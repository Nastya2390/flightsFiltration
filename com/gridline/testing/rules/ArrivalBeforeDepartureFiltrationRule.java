package com.gridline.testing.rules;

import com.gridline.testing.Flight;

import java.util.List;
import java.util.stream.Collectors;

public class ArrivalBeforeDepartureFiltrationRule implements FiltrationRule {
    List<Flight> flightList;

    public ArrivalBeforeDepartureFiltrationRule(List<Flight> flightList) {
        this.flightList = flightList;
    }

    @Override
    public List<Flight> filter() {
        return flightList.stream().map(x -> x.getSegments().stream()
                .filter(y -> y.getDepartureDate().compareTo(y.getArrivalDate()) > 0)
                .collect(Collectors.toList()))
                .filter(x -> !x.isEmpty())
                .map(Flight::new)
                .collect(Collectors.toList());
    }
}
