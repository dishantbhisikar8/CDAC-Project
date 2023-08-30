package com.airlinereservation.resource;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.airlinereservation.dto.CommonApiResponse;
import com.airlinereservation.dto.FlightBookingRequestDto;
import com.airlinereservation.dto.FlightBookingResponseDto;
import com.airlinereservation.entity.Airplane;
import com.airlinereservation.entity.Flight;
import com.airlinereservation.entity.FlightBooking;
import com.airlinereservation.entity.User;
import com.airlinereservation.service.FlightBookingService;
import com.airlinereservation.service.FlightService;
import com.airlinereservation.service.UserService;
import com.airlinereservation.utility.Constants.FlightBookingStatus;
import com.airlinereservation.utility.Constants.FlightClassType;
import com.airlinereservation.utility.IdGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class FlightBookingResource {

	private final Logger LOG = LoggerFactory.getLogger(FlightBookingResource.class);

	private ObjectMapper objectMapper = new ObjectMapper();

	@Autowired
	private FlightBookingService flightBookingService;

	@Autowired
	private FlightService flightService;

	@Autowired
	private UserService userService;

	public ResponseEntity<CommonApiResponse> addFlightBooking(FlightBookingRequestDto request) {

		LOG.info("Received request for add flight");

		CommonApiResponse response = new CommonApiResponse();

		if (request.getFlightClassType() == null || request.getTotalPassengers() == 0
				|| request.getFlightId() == 0 | request.getPassengerId() == 0) {

			response.setResponseMessage("missing data");
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
		}

		Flight flight = this.flightService.getById(request.getFlightId());

		Airplane airplane = flight.getAirplane();

		User passenger = this.userService.getUserById(request.getPassengerId());

		String bookingTime = String
				.valueOf(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());

		FlightBooking booking = new FlightBooking();

		String bookingId = IdGenerator.generateBookingId();
		booking.setFlightClass(request.getFlightClassType());

		if (request.getFlightClassType().equals(FlightClassType.ECONOMY.value())) {

			if (flight.getEconomySeatsAvailable() < request.getTotalPassengers()) {
				response.setResponseMessage("Seat Unavailable, Failed to reserve the seat");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			BigDecimal totalFare = flight.getEconomySeatFare()
					.multiply(BigDecimal.valueOf(request.getTotalPassengers()));

			if (passenger.getWalletAmount().compareTo(totalFare) < 0) {
				response.setResponseMessage("Insufficient Funds in Passenger Wallet");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			booking.setBookingId(bookingId);
			booking.setBookingTime(bookingTime);
			booking.setFlight(flight);
			booking.setPassenger(passenger);
			booking.setStatus(FlightBookingStatus.CONFIRMED.value());
			booking.setTotalPassengers(request.getTotalPassengers());

			FlightBooking passengerBooking = this.flightBookingService.add(booking);

			flight.setEconomySeatsAvailable(flight.getEconomySeatsAvailable() - request.getTotalPassengers());
			flightService.add(flight);
			
			passenger.setWalletAmount(passenger.getWalletAmount().subtract(totalFare));
			this.userService.updateUser(passenger);

			response.setResponseMessage("Booking Success, your booking id is " + bookingId);
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

		}

		else if (request.getFlightClassType().equals(FlightClassType.BUSINESS.value())) {

			if (flight.getBusinessSeatsAvailable() < request.getTotalPassengers()) {
				response.setResponseMessage("Seat Unavailable, Failed to reserve the seat");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			BigDecimal totalFare = flight.getBusinessSeatFare()
					.multiply(BigDecimal.valueOf(request.getTotalPassengers()));

			if (passenger.getWalletAmount().compareTo(totalFare) < 0) {
				response.setResponseMessage("Insufficient Funds in Passenger Wallet");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			booking.setBookingId(bookingId);
			booking.setBookingTime(bookingTime);
			booking.setFlight(flight);
			booking.setPassenger(passenger);
			booking.setStatus(FlightBookingStatus.CONFIRMED.value());
			booking.setTotalPassengers(request.getTotalPassengers());

			FlightBooking passengerBooking = this.flightBookingService.add(booking);

			flight.setBusinessSeatsAvailable(flight.getBusinessSeatsAvailable() - request.getTotalPassengers());
			flightService.add(flight);
			
			passenger.setWalletAmount(passenger.getWalletAmount().subtract(totalFare));
			this.userService.updateUser(passenger);

			response.setResponseMessage("Booking Success, your booking id is " + bookingId);
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

		}

		else if (request.getFlightClassType().equals(FlightClassType.FIRST_CLASS.value())) {

			if (flight.getFirstClassSeatsAvailable() < request.getTotalPassengers()) {
				response.setResponseMessage("Seat Unavailable, Failed to reserve the seat");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			BigDecimal totalFare = flight.getFirstClassSeatFare()
					.multiply(BigDecimal.valueOf(request.getTotalPassengers()));

			if (passenger.getWalletAmount().compareTo(totalFare) < 0) {
				response.setResponseMessage("Insufficient Funds in Passenger Wallet");
				response.setSuccess(true);

				return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
			}

			booking.setBookingId(bookingId);
			booking.setBookingTime(bookingTime);
			booking.setFlight(flight);
			booking.setPassenger(passenger);
			booking.setStatus(FlightBookingStatus.CONFIRMED.value());
			booking.setTotalPassengers(request.getTotalPassengers());

			FlightBooking passengerBooking = this.flightBookingService.add(booking);

			flight.setFirstClassSeatsAvailable(flight.getFirstClassSeatsAvailable() - request.getTotalPassengers());
			flightService.add(flight);
			
			passenger.setWalletAmount(passenger.getWalletAmount().subtract(totalFare));
			this.userService.updateUser(passenger);

			response.setResponseMessage("Booking Success, your booking id is " + bookingId);
			response.setSuccess(true);

			return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);

		}

		response.setResponseMessage("Failed to book the seats");
		response.setSuccess(true);

		return new ResponseEntity<CommonApiResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	public ResponseEntity<FlightBookingResponseDto> fetchAllFlightBookings() {

		LOG.info("Received request for fetching all bookings");

		FlightBookingResponseDto response = new FlightBookingResponseDto();

		List<FlightBooking> allBookings = new ArrayList<>();

		allBookings = this.flightBookingService.getAll();

		if (CollectionUtils.isEmpty(allBookings)) {
			response.setResponseMessage("Failed to book the seats");
			response.setSuccess(true);

			return new ResponseEntity<FlightBookingResponseDto>(response, HttpStatus.OK);
		}

		response.setBookings(allBookings);
		response.setResponseMessage("Failed to book the seats");
		response.setSuccess(true);

		// Convert the object to a JSON string
		String jsonString = null;
		try {
			jsonString = objectMapper.writeValueAsString(response);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return new ResponseEntity<FlightBookingResponseDto>(response, HttpStatus.OK);
	}

	public ResponseEntity<FlightBookingResponseDto> fetchUserBookings(int userId) {

		LOG.info("Received request for fetching user bookings");

		FlightBookingResponseDto response = new FlightBookingResponseDto();
		
		if(userId == 0) {
			response.setResponseMessage("missing data");
			response.setSuccess(true);

			return new ResponseEntity<FlightBookingResponseDto>(response, HttpStatus.BAD_REQUEST);
		}
		
		User passenger = this.userService.getUserById(userId);

		List<FlightBooking> allBookings = new ArrayList<>();

		allBookings = this.flightBookingService.getByPassenger(passenger);

		if (CollectionUtils.isEmpty(allBookings)) {
			response.setResponseMessage("Failed to book the seats");
			response.setSuccess(true);

			return new ResponseEntity<FlightBookingResponseDto>(response, HttpStatus.OK);
		}

		response.setBookings(allBookings);
		response.setResponseMessage("Failed to book the seats");
		response.setSuccess(true);

		return new ResponseEntity<FlightBookingResponseDto>(response, HttpStatus.OK);
	}

}
