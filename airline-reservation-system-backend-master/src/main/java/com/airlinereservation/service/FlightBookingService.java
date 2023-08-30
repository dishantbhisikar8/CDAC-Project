package com.airlinereservation.service;

import java.util.List;

import com.airlinereservation.entity.Flight;
import com.airlinereservation.entity.FlightBooking;
import com.airlinereservation.entity.User;

public interface FlightBookingService {
	
	FlightBooking add(FlightBooking flightBooking);
	FlightBooking getById(int id);
	List<FlightBooking> getAll();
	List<FlightBooking> getByPassenger(User user);
	List<FlightBooking> getByFlight(Flight flight);

}
