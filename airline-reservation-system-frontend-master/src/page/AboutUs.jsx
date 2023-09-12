import { useState } from "react";
import { toast } from "react-toastify"; // Import the toast module

const AboutUs = () => {
  const [feedback, setFeedback] = useState({ bookingId: "", cmmt: "" }); // Note that I've changed the initial state to an object

  const handleSubmit = () => {
    // Your logic to handle form submission
    // For now, let's just show the toast message and clear the fields
    toast.success("Feedback submitted", {
      position: "top-center",
      autoClose: 1000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });

    setFeedback({ bookingId: "", cmmt: "" }); // Clear the fields
  };

  return (
    <div className="text-color ms-5 me-5 mr-5 mt-3">
      <div>
        <input
          type="text"
          placeholder="Enter bookingId"
          value={feedback.bookingId}
          onChange={(e) => setFeedback({ ...feedback, bookingId: e.target.value })}
        />
        
        <textarea
          name="feedback"
          id="feedback"
          className="form-control"
          rows="3"
          required="required"
          placeholder="Your feedback"
          value={feedback.cmmt}
          onChange={(e) => setFeedback({ ...feedback, cmmt: e.target.value })}
        />
        
        <button type="button" className="btn btn-primary" onClick={handleSubmit}>
          Submit
        </button>
      </div>
    </div>
  );
};

export default AboutUs;
