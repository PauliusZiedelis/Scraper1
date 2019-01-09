package servises;

import java.util.ArrayList;
import constants.FlightConstants;

public class UrlConstuctor {
	private static String A_City;
	private static String AdultCount;
	private static String ChildCount;
	private static String CurrencyCode;
	private static String D_City;
	private static String D_Day;
	private static String D_Month;
	private static String D_SelectedDay;
	private static String IncludeTransit;
	private static String InfantCount;
	private static String R_Day;
	private static String R_Month;
	private static String R_SelectedDay;
	private static String TripType;
	private static String mode;

	public static String getUrl(String date1) {
		try {
			String date2 = date1;
			FlightConstants con = new FlightConstants();

			ArrayList<String> setingsList = new ArrayList<String>();
			setingsList.add(A_City = con.A_City);
			setingsList.add(AdultCount = con.AdultCount);
			setingsList.add(ChildCount = con.ChildCount);
			setingsList.add(CurrencyCode = con.CurrencyCode);
			setingsList.add(D_City = con.D_City);
			setingsList.add(D_Day = date1.substring(8, 10));
			setingsList.add(D_Month = date1.substring(0, 7).replace("-", ""));
			setingsList.add(D_SelectedDay = date1.substring(8, 10));
			setingsList.add(IncludeTransit = con.IncludeTransit);
			setingsList.add(InfantCount = con.InfantCount);
			setingsList.add(R_Day = date2.substring(8, 10));
			setingsList.add(R_Month = date2.substring(0, 7).replace("-", ""));
			setingsList.add(R_SelectedDay = date2.substring(8, 10));
			setingsList.add(TripType = con.TripType);
			setingsList.add(mode = con.mode);
			setingsList.getClass().getDeclaredFields();

			StringBuilder url = new StringBuilder();
			url.append("https://www.norwegian.com/uk/ipc/availability/avaday?").append(con.el1).append("=")
					.append(setingsList.get(0)).append("&").append(con.el2).append("=").append(setingsList.get(1))
					.append("&").append(con.el3).append("=").append(setingsList.get(2)).append("&").append(con.el4)
					.append("=").append(setingsList.get(3)).append("&").append(con.el5).append("=")
					.append(setingsList.get(4)).append("&").append(con.el6).append("=").append(setingsList.get(5))
					.append("&").append(con.el7).append("=").append(setingsList.get(6)).append("&").append(con.el8)
					.append("=").append(setingsList.get(7)).append("&").append(con.el9).append("=")
					.append(setingsList.get(8)).append("&").append(con.el10).append("=").append(setingsList.get(9))
					.append("&").append(con.el11).append("=").append(setingsList.get(10)).append("&").append(con.el12)
					.append("=").append(setingsList.get(11)).append("&").append(con.el13).append("=")
					.append(setingsList.get(12)).append("&").append(con.el14).append("=").append(setingsList.get(13))
					.append(con.el15).append("=").append(setingsList.get(14));
			return url.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
}
