package com.airlinereservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.airlinereservation.dao.FlightBookingDao;
import com.airlinereservation.entity.Flight;
import com.airlinereservation.entity.FlightBooking;
import com.airlinereservation.entity.User;

@Service
public class FlightBookingServiceImpl implements FlightBookingService {
	
	@Autowired
	private FlightBookingDao flightBookingDao;

	@Override
	public FlightBooking add(FlightBooking flightBooking) {
		// TODO Auto-generated method stub
		return flightBookingDao.save(flightBooking);
	}

	@Override
	public FlightBooking getById(int id) {
		// TODO Auto-generated method stub
		return flightBookingDao.findById(id).get();
	}

	@Override
	public List<FlightBooking> getAll() {
		// TODO Auto-generated method stub
		return flightBookingDao.findAll();
	}

	@Override
	public List<FlightBooking> getByPassenger(User user) {
		// TODO Auto-generated method stub
		return flightBookingDao.findByPassenger(user);
	}

	@Override
	public List<FlightBooking> getByFlight(Flight flight) {
		// TODO Auto-generated method stub
		return flightBookingDao.findByFlight(flight);
	}

}
