import { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import axios from "axios";
import AdminModal from "../../components/AdminModal";

const ManageQuestion = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const [maintenanceDone, setMaintenanceDone] = useState(false);
  
  const [question, setQuestion] = useState({
    questionTitle: "",
    option1: "",
    option2: "",
    option3: "",
    option4: "",
    answer: "",
    difficultyLevel: "Easy",
    category: "",
  });

  useEffect(() => {
    if (id) {
      axios.get(`${process.env.REACT_APP_API_QUESTION_URL}/${id}`)
        .then(response => setQuestion(response.data))
        .catch(error => console.error("Error fetching question:", error));
    }
  }, [id]);

  const handleChange = (e) => {
    setQuestion({ ...question, [e.target.name]: e.target.value });
  };

  const handleSubmit = () => {
    if (id) {
      axios.put(`${process.env.REACT_APP_API_QUESTION_URL}`, question)
        .then(() => setMaintenanceDone(true))
        .catch(error => console.error("Error updating question:", error));
    } else {
      axios.post(`${process.env.REACT_APP_API_QUESTION_URL}`, question)
        .then(() => setMaintenanceDone(true))
        .catch(error => console.error("Error adding question:", error));
    }
  };

  return (
    <div className="max-w-3xl mx-auto bg-white p-6 rounded shadow-md mt-6">
      <h2 className="text-2xl font-bold mb-4">{id ? "Edit Question" : "Add New Question"}</h2>

      {/* Question Title */}
      <input 
        name="questionTitle"
        value={question.questionTitle}
        onChange={handleChange}
        placeholder="Enter question"
        className="border p-2 w-full mb-3"
      />

      {/* Options */}
      <input name="option1" value={question.option1} onChange={handleChange} placeholder="Option 1" className="border p-2 w-full mb-2" />
      <input name="option2" value={question.option2} onChange={handleChange} placeholder="Option 2" className="border p-2 w-full mb-2" />
      <input name="option3" value={question.option3} onChange={handleChange} placeholder="Option 3" className="border p-2 w-full mb-2" />
      <input name="option4" value={question.option4} onChange={handleChange} placeholder="Option 4" className="border p-2 w-full mb-4" />

      {/* Correct Answer (Dropdown) */}
      <select 
        name="answer"
        value={question.answer}
        onChange={handleChange}
        className="border p-2 w-full mb-4"
      >
        <option value="">Select Correct Answer</option>
        {question.option1 && <option value={question.option1}>{question.option1}</option>}
        {question.option2 && <option value={question.option2}>{question.option2}</option>}
        {question.option3 && <option value={question.option3}>{question.option3}</option>}
        {question.option4 && <option value={question.option4}>{question.option4}</option>}
      </select>

      {/* Difficulty Level */}
      <select 
        name="difficultyLevel"
        value={question.difficultyLevel}
        onChange={handleChange}
        className="border p-2 w-full mb-4"
      >
        <option value="Easy">Easy</option>
        <option value="Medium">Medium</option>
        <option value="Hard">Hard</option>
      </select>

      {/* Category */}
      <input 
        name="category"
        value={question.category}
        onChange={handleChange}
        placeholder="Category"
        className="border p-2 w-full mb-4"
      />

      {/* Submit Button */}
      <button onClick={handleSubmit} className="bg-blue-500 text-white px-4 py-2 rounded">
        {id ? "Update" : "Add"} Question
      </button>

      {maintenanceDone && <AdminModal message={ id ? "Question updated successfully" : "Question added successfully"} navigateTo="/admin"/>}
    </div>
  );
};

export default ManageQuestion;
