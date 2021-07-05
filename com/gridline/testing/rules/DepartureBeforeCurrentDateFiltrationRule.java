package com.gridline.testing.rules;

import com.gridline.testing.Flight;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class DepartureBeforeCurrentDateFiltrationRule implements FiltrationRule {
    List<Flight> flightList;

    public DepartureBeforeCurrentDateFiltrationRule(List<Flight> flightList) {
        this.flightList = flightList;
    }

    @Override
    public List<Flight> filter() {
        return flightList.stream().map(x -> x.getSegments().stream()
                .filter(y -> y.getDepartureDate().compareTo(LocalDateTime.now()) < 0)
                .collect(Collectors.toList()))
                .filter(x -> !x.isEmpty())
                .map(Flight::new)
                .collect(Collectors.toList());
    }
}
