package main;

import servises.Scraper;

public class Main {

	public static void main(String[] args) {
		String date1 = "2019-02-01";
		String date2 = "2019-02-28";
		Scraper scrap = new Scraper();
		scrap.scrap(date1, date2);

	}

}
