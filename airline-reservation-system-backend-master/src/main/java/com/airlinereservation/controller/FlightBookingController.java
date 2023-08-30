package com.airlinereservation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.airlinereservation.dto.CommonApiResponse;
import com.airlinereservation.dto.FlightBookingRequestDto;
import com.airlinereservation.dto.FlightBookingResponseDto;
import com.airlinereservation.resource.FlightBookingResource;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("api/flight/book")
@CrossOrigin(origins = "http://localhost:3000")
public class FlightBookingController {
	
	@Autowired
	private FlightBookingResource flightBookingResource;
	
	@PostMapping("add")
	@ApiOperation(value = "Api to add the Flight Booking")
	public ResponseEntity<CommonApiResponse> addAirport(@RequestBody FlightBookingRequestDto request) {
		return this.flightBookingResource.addFlightBooking(request);
	}
	
	@GetMapping("/fetch/all")
	@ApiOperation(value = "Api to fetch all flight bookings")
	public ResponseEntity<FlightBookingResponseDto> fetchAllFlights() {
		return this.flightBookingResource.fetchAllFlightBookings();
	}
	
	@GetMapping("/fetch/user")
	@ApiOperation(value = "Api to fetch user flight bookings")
	public ResponseEntity<FlightBookingResponseDto> fetchUserBookings(@RequestParam("userId") int userId) {
		return this.flightBookingResource.fetchUserBookings(userId);
	}

}