import React from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import { useState, useEffect } from "react";
import { ToastContainer, toast } from "react-toastify";

const ViewAllFlightBooking = () => {
  const navigate = useNavigate();

  const [bookedFlights, setBookedFlights] = useState([]);

  const retrieveAllBookedFlights = async () => {
    const response = await axios.get(
      "http://localhost:8080/api/flight/book/fetch/all"
    );
    console.log(response.data);
    return response.data;
  };

  const convertToEpochTime = (dateString) => {
    const selectedDate = new Date(dateString);
    const epochTime = selectedDate.getTime();
    return epochTime;
  };

  useEffect(() => {
    const getAllBookedFlights = async () => {
      const bookings = await retrieveAllBookedFlights();
      if (bookings) {
        setBookedFlights(bookings.bookings);
      }
    };

    getAllBookedFlights();
  }, []);

  const formatDateFromEpoch = (epochTime) => {
    const date = new Date(Number(epochTime));
    const formattedDate = date.toLocaleString(); // Adjust the format as needed

    return formattedDate;
  };

  return (
    <div className="mt-3">
      <div
        className="card form-card ms-2 me-2 mb-5 custom-bg border-color "
        style={{
          height: "45rem",
        }}
      >
        <div className="card-header custom-bg-text text-center bg-color">
          <h2>Booked Flights</h2>
        </div>
        <div
          className="card-body"
          style={{
            overflowY: "auto",
          }}
        >
          <div className="table-responsive mt-3">
            <table className="table table-hover text-color text-center">
              <thead className="table-bordered border-color bg-color custom-bg-text">
                <tr>
                  <th scope="col">Booking Id</th>
                  <th scope="col">Passenger Name</th>
                  <th scope="col">Passenger Contact</th>
                  <th scope="col">Flight Number</th>
                  <th scope="col">Airplane</th>
                  <th scope="col">Airplane Registration No.</th>
                  <th scope="col">Departure Time</th>
                  <th scope="col">Arrival Time</th>
                  <th scope="col">Source Airport</th>
                  <th scope="col">Destination Airport</th>
                  <th scope="col">Flight Class</th>
                  <th scope="col">Seat Fare (Rs.)</th>
                  <th scope="col">Total Passenger</th>
                  <th scope="col">Booking Time</th>
                  <th scope="col">Status</th>
                </tr>
              </thead>
              <tbody>
                {bookedFlights.map((book) => {
                  return (
                    <tr>
                      <td>
                        <b>{book.bookingId}</b>
                      </td>
                      <td>
                        <b>{book.passenger.name}</b>
                      </td>
                      <td>
                        <b>{book.passenger.contact}</b>
                      </td>
                      <td>
                        <b>{book.flight.flightNumber}</b>
                      </td>
                      <td>
                        <b>{book.flight.airplane.name}</b>
                      </td>
                      <td>
                        <b>{book.flight.airplane.registrationNumber}</b>
                      </td>
                      <td>
                        <b>{formatDateFromEpoch(book.flight.departureTime)}</b>
                      </td>
                      <td>
                        <b>{formatDateFromEpoch(book.flight.arrivalTime)}</b>
                      </td>
                      <td>
                        <b>{book.flight.departureAirport.name}</b>
                      </td>
                      <td>
                        <b>{book.flight.arrivalAirport.name}</b>
                      </td>
                      <td>
                        <b>{book.flightClass}</b>
                      </td>
                      <td>
                        {(() => {
                          if (book.flightClass === "Economy") {
                            return <b>{book.flight.economySeatFare}</b>;
                          } else if (book.flightClass === "Business") {
                            return <b>{book.flight.businessSeatFare}</b>;
                          } else if (book.flightClass === "First Class") {
                            return <b>{book.flight.firstClassSeatFare}</b>;
                          }
                        })()}
                      </td>
                      <td>
                        <b>{book.totalPassengers}</b>
                      </td>
                      <td>
                        <b>{formatDateFromEpoch(book.bookingTime)}</b>
                      </td>
                      <td>
                        <b>{book.status}</b>
                      </td>
                    </tr>
                  );
                })}
              </tbody>
            </table>
          </div>
        </div>
      </div>
    </div>
  );
};

export default ViewAllFlightBooking;
