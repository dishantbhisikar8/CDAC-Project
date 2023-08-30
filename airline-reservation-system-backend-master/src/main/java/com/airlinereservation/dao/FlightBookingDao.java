package com.airlinereservation.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.airlinereservation.entity.Flight;
import com.airlinereservation.entity.FlightBooking;
import com.airlinereservation.entity.User;

@Repository
public interface FlightBookingDao extends JpaRepository<FlightBooking, Integer> {
	
	List<FlightBooking> findByPassenger(User user);
	List<FlightBooking> findByFlight(Flight flight);

}
