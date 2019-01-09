package servises;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import models.DTO.FlightInfoDTO;

public class Scraper {

	public void scrap(String date1, String date2) {
		try {
			ArrayList<FlightInfoDTO> flyghtList = new ArrayList<FlightInfoDTO>();
			String str_date = date1;
			String end_date = date2;
			List<Date> dates = new ArrayList<Date>();
			DateFormat formatter;
			formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date startDate = (Date) formatter.parse(str_date);
			Date endDate = (Date) formatter.parse(end_date);
			long interval = 24 * 60 * 60 * 1000;
			long endTime = endDate.getTime();
			long curTime = startDate.getTime();
			while (curTime <= endTime) {
				dates.add(new Date(curTime));
				curTime += interval;
			}

			for (int i = 0; i < dates.size(); i++) {
				Date lDate = (Date) dates.get(i);
				String date = formatter.format(lDate);
				UrlConstuctor url = new UrlConstuctor();
				flyghtList.add(getData(url.getUrl(date), date));
			}
			SaveInfo save = new SaveInfo();
			save.save(flyghtList);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static FlightInfoDTO getData(String url, String date) {
		try {
			FlightInfoDTO info = new FlightInfoDTO();
			Document doc = Jsoup.connect(url).ignoreHttpErrors(true).timeout(10000).header("pr1cd38", "StandardLowFare")
					.method(Connection.Method.GET).get();
			if (flyingToday(doc) == true) {
				Elements rowsInfo1 = doc.getElementsByClass("oddrow rowinfo1 ");
				Elements rowsInfo2 = doc.getElementsByClass("oddrow rowinfo2");
				Elements lastRow = doc.getElementsByClass("oddrow .lastrow");
				info.doesFly = true;
				info.date = date;
				info.departureTime = rowsInfo1.select(".depdest .content").first().html().toString();
				info.arrivalTime = rowsInfo1.select(".arrdest .content").first().html();
				info.departureAirport = rowsInfo2.select(".depdest .content").first().html();
				info.arrivalAirport = rowsInfo2.select(".arrdest .content").first().html();
				info.cheapestPrice = rowsInfo1.select(".fareselect.standardlowfare .label").first().html();
				return info;
				// System.out.println(info.date + " " + info.departureAirport + " - " +
				// info.arrivalAirport + " "
				// + info.departureTime + " " + info.arrivalTime + " " + info.cheapestPrice);
			} else {
				info.date = date;
				info.doesFly = false;
				return info;
			}

			// System.out.println(info.date + " " + info.departureAirport + " - " +
			// info.arrivalAirport + " "
			// + info.departureTime + " " + info.arrivalTime + " " + info.cheapestPrice);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	private static boolean flyingToday(Document doc) {
		if (doc.getElementsByClass("avadaytable").isEmpty()) {
			return false;
		}
		return true;
	}

}
