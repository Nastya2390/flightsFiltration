package com.gridline.testing.test;

import com.gridline.testing.Flight;
import com.gridline.testing.FlightBuilder;
import com.gridline.testing.rules.ArrivalBeforeDepartureFiltrationRule;
import com.gridline.testing.rules.DepartureBeforeCurrentDateFiltrationRule;
import com.gridline.testing.rules.TwoHoursTimeBetweenSegmentsFiltrationRule;
import org.junit.jupiter.api.Test;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

public class RulesTest {

    @Test
    public void filtrationCheck1() {
        LocalDateTime twoDaysBeforeNow = LocalDateTime.now().minusDays(2);
        Flight flight = FlightBuilder.createFlight(twoDaysBeforeNow, twoDaysBeforeNow.plusHours(2));
        List<Flight> filteredFlights = new DepartureBeforeCurrentDateFiltrationRule(Collections.singletonList(flight)).filter();
        Assert.assertFalse(filteredFlights.isEmpty());
        Assert.assertEquals(filteredFlights.size(), 1);
        Assert.assertEquals(flight.toString(), filteredFlights.iterator().next().toString());
    }

    @Test
    public void filtrationCheck2() {
        LocalDateTime twoDaysFromNow = LocalDateTime.now().plusDays(2);
        Flight flight = FlightBuilder.createFlight(twoDaysFromNow, twoDaysFromNow.plusHours(2));
        List<Flight> filteredFlights = new DepartureBeforeCurrentDateFiltrationRule(Collections.singletonList(flight)).filter();
        Assert.assertTrue(filteredFlights.isEmpty());
    }

    @Test
    public void filtrationCheck3() {
        LocalDateTime twoDaysFromNow = LocalDateTime.now().plusDays(2);
        Flight flight = FlightBuilder.createFlight(twoDaysFromNow, twoDaysFromNow.minusHours(2));
        List<Flight> filteredFlights = new ArrivalBeforeDepartureFiltrationRule(Collections.singletonList(flight)).filter();
        Assert.assertFalse(filteredFlights.isEmpty());
        Assert.assertEquals(filteredFlights.size(), 1);
        Assert.assertEquals(flight.toString(), filteredFlights.iterator().next().toString());
    }

    @Test
    public void filtrationCheck4() {
        LocalDateTime twoDaysFromNow = LocalDateTime.now().plusDays(2);
        Flight flight = FlightBuilder.createFlight(twoDaysFromNow, twoDaysFromNow.plusHours(2));
        List<Flight> filteredFlights = new ArrivalBeforeDepartureFiltrationRule(Collections.singletonList(flight)).filter();
        Assert.assertTrue(filteredFlights.isEmpty());
    }

    @Test
    public void filtrationCheck5() {
        LocalDateTime twoDaysFromNow = LocalDateTime.now().plusDays(2);
        Flight flight = FlightBuilder.createFlight(twoDaysFromNow, twoDaysFromNow.plusHours(2), twoDaysFromNow.plusHours(4), twoDaysFromNow.plusHours(6));
        List<Flight> filteredFlights = new TwoHoursTimeBetweenSegmentsFiltrationRule(Collections.singletonList(flight)).filter();
        Assert.assertTrue(filteredFlights.isEmpty());
    }

    @Test
    public void filtrationCheck6() {
        LocalDateTime twoDaysFromNow = LocalDateTime.now().plusDays(2);
        Flight flight = FlightBuilder.createFlight(twoDaysFromNow, twoDaysFromNow.plusHours(2), twoDaysFromNow.plusHours(4).plusSeconds(1), twoDaysFromNow.plusHours(6));
        List<Flight> filteredFlights = new TwoHoursTimeBetweenSegmentsFiltrationRule(Collections.singletonList(flight)).filter();
        Assert.assertFalse(filteredFlights.isEmpty());
        Assert.assertEquals(filteredFlights.size(), 1);
        Assert.assertEquals(flight.toString(), filteredFlights.iterator().next().toString());
    }

    @Test
    public void filtrationCheck7() {
        LocalDateTime twoDaysFromNow = LocalDateTime.now().plusDays(2);
        Flight flight = FlightBuilder.createFlight(twoDaysFromNow, twoDaysFromNow.plusHours(2), twoDaysFromNow.plusHours(3), twoDaysFromNow.plusHours(5),
                twoDaysFromNow.plusHours(6), twoDaysFromNow.plusHours(8));
        List<Flight> filteredFlights = new TwoHoursTimeBetweenSegmentsFiltrationRule(Collections.singletonList(flight)).filter();
        Assert.assertTrue(filteredFlights.isEmpty());
    }

    @Test
    public void filtrationCheck8() {
        LocalDateTime twoDaysFromNow = LocalDateTime.now().plusDays(2);
        Flight flight = FlightBuilder.createFlight(twoDaysFromNow, twoDaysFromNow.plusHours(2), twoDaysFromNow.plusHours(3), twoDaysFromNow.plusHours(5),
                twoDaysFromNow.plusHours(6), twoDaysFromNow.plusHours(8), twoDaysFromNow.plusHours(9), twoDaysFromNow.plusHours(11));
        List<Flight> filteredFlights = new TwoHoursTimeBetweenSegmentsFiltrationRule(Collections.singletonList(flight)).filter();
        Assert.assertFalse(filteredFlights.isEmpty());
        Assert.assertEquals(filteredFlights.size(), 1);
        Assert.assertEquals(flight.toString(), filteredFlights.iterator().next().toString());
    }

}
