package com.gridline.testing;

import com.gridline.testing.rules.ArrivalBeforeDepartureFiltrationRule;
import com.gridline.testing.rules.DepartureBeforeCurrentDateFiltrationRule;
import com.gridline.testing.rules.TwoHoursTimeBetweenSegmentsFiltrationRule;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Flight> flightList = FlightBuilder.createFlights();
        System.out.println("1. Вылет до текущего момента времени:");
        printFlights(new DepartureBeforeCurrentDateFiltrationRule(flightList).filter());
        System.out.println("2. Имеются сегменты с датой прилёта раньше даты вылета:");
        printFlights(new ArrivalBeforeDepartureFiltrationRule(flightList).filter());
        System.out.println("3. Общее время, проведённое на земле, превышает два часа:");
        printFlights(new TwoHoursTimeBetweenSegmentsFiltrationRule(flightList).filter());
    }

    private static void printFlights(List<Flight> flightList) {
        flightList.forEach(System.out::println);
    }
}


