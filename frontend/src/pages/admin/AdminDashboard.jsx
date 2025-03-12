import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";

const AdminDashboard = () => {
  const [activeTab, setActiveTab] = useState("quizzes");
  const [quizzes, setQuizzes] = useState([]);
  const [questions, setQuestions] = useState([]);
  const navigate = useNavigate();

  useEffect(() => {
    fetchQuizzes();
    fetchQuestions();
  }, []);

  const fetchQuizzes = async () => {
    try {
      const response = await axios.get(`${process.env.REACT_APP_API_QUIZ_URL}/all`);
      setQuizzes(response.data);
    } catch (error) {
      console.error("Error fetching quizzes:", error);
    }
  };

  const fetchQuestions = async () => {
    try {
      const response = await axios.get(`${process.env.REACT_APP_API_QUESTION_URL}/all`);
      setQuestions(response.data);
    } catch (error) {
      console.error("Error fetching questions:", error);
    }
  };

  const handleDeleteQuestion = async (id) => {
    try {
      const response = await axios.delete(`${process.env.REACT_APP_API_QUESTION_URL}/${id}`);
      setQuestions(questions.filter(q => q.id != id));
    } catch (error) {
      console.error("Error deleting question: ", error);
    }
  }

  return (
    <div className="w-full min-h-screen m-5 p-4 bg-white shadow-lg rounded-lg">
      <h1 className="text-2xl font-bold text-center mb-4">Admin Dashboard</h1>
      
      {/* Tabs */}
      <div className="flex border-b mb-4">
        <button
          className={`p-2 flex-1 text-center ${activeTab === "quizzes" ? "border-b-4 border-blue-500 font-semibold" : "text-gray-500"}`}
          onClick={() => setActiveTab("quizzes")}
        >
          Quizzes
        </button>
        <button
          className={`p-2 flex-1 text-center ${activeTab === "questions" ? "border-b-4 border-blue-500 font-semibold" : "text-gray-500"}`}
          onClick={() => setActiveTab("questions")}
        >
          Questions
        </button>
      </div>

      {/* Quizzes Tab */}
      {activeTab === "quizzes" && (
  <div>
    <button className="bg-green-500 text-white px-4 py-2 rounded mb-4" onClick={() => navigate("/admin/quiz/new")}>+ Create New Quiz</button>
    <div className="overflow-x-auto">
      <table className="min-w-full bg-white border border-gray-300 shadow-md rounded-lg">
        <thead className="bg-gray-200">
          <tr>
            <th className="py-2 px-4 border-b text-left">Quiz Title</th>
            <th className="py-2 px-4 border-b text-center">Actions</th>
          </tr>
        </thead>
        <tbody>
          {quizzes.map((quiz) => (
            <tr key={quiz.id} className="border-b hover:bg-gray-100">
              <td className="py-2 px-4">{quiz.title}</td>
              <td className="py-2 px-4 text-center">
                <button className="bg-red-500 text-white px-3 py-1 rounded">Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  </div>
)}


      {/* Questions Tab */}
      {activeTab === "questions" && (
  <div>
    <button className="bg-green-500 text-white px-4 py-2 rounded mb-4" onClick={()=>navigate("/admin/question/new")}>+ Add New Question</button>
    <div className="overflow-x-auto">
      <table className="min-w-full bg-white border border-gray-300 shadow-md rounded-lg">
        <thead className="bg-gray-200">
          <tr>
            <th className="py-2 px-4 border-b text-left">Question</th>
            <th className="py-2 px-4 border-b text-left">Category</th>
            <th className="py-2 px-4 border-b text-left">Difficulty</th>
            <th className="py-2 px-4 border-b text-center">Actions</th>
          </tr>
        </thead>
        <tbody>
          {questions.map((q) => (
            <tr key={q.id} className="border-b hover:bg-gray-100">
              <td className="py-2 px-4">{q.questionTitle}</td>
              <td className="py-2 px-4">{q.category}</td>
              <td className="py-2 px-4">{q.difficultyLevel}</td>
              <td className="py-2 px-4 text-center">
                <button className="bg-blue-500 text-white px-3 py-1 rounded mr-2" onClick={() => navigate(`/admin/question/${q.id}`)}>Edit</button>
                <button className="bg-red-500 text-white px-3 py-1 rounded" onClick={() => handleDeleteQuestion(q.id)}>Delete</button>
              </td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  </div>
)}

    </div>
  );
};

export default AdminDashboard;
