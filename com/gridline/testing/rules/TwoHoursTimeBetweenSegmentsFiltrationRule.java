package com.gridline.testing.rules;

import com.gridline.testing.Flight;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TwoHoursTimeBetweenSegmentsFiltrationRule implements FiltrationRule {
    List<Flight> flightList;

    public TwoHoursTimeBetweenSegmentsFiltrationRule(List<Flight> flightList) {
        this.flightList = flightList;
    }

    @Override
    public List<Flight> filter() {
        List<Flight> twoHoursTransferFlights = new ArrayList<>();
        List<Flight> transferFlights = flightList.stream().filter(x -> x.getSegments().size() > 1).collect(Collectors.toList());
        for (Flight flight : transferFlights) {
            long transferTime = 0L;
            for (int i = 0; i < flight.getSegments().size() - 1; i++) {
                transferTime += Duration.between(flight.getSegments().get(i).getArrivalDate(), flight.getSegments().get(i + 1).getDepartureDate()).toSeconds();
            }
            if (transferTime > 2 * 3600) twoHoursTransferFlights.add(flight);
        }
        return twoHoursTransferFlights;
    }
}
