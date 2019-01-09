package servises;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.List;
import models.DTO.FlightInfoDTO;

public class SaveInfo {
	public static void save(List<FlightInfoDTO> flightsList) {
		BufferedWriter output = null;
		try {
			File file = new File("flightInfo.txt");
			output = new BufferedWriter(new FileWriter(file));
			for (int i = 0; i < flightsList.size(); i++) {
				if (flightsList.get(i).doesFly) {
					output.write(flightsList.get(i).date + " from: " + flightsList.get(i).departureAirport + " to: "
							+ flightsList.get(i).arrivalAirport + " trip time: " + flightsList.get(i).departureTime
							+ " - " + flightsList.get(i).arrivalTime + "cheapest price: "
							+ flightsList.get(i).cheapestPrice+" EUR");
					output.newLine();
				} else {
					output.write(flightsList.get(i).date + " Don`t flying");
					output.newLine();
				}

			}
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
