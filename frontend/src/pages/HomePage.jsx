// src/pages/HomePage.js
import { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import axios from "axios";

const HomePage = () => {
  const [quizzes, setQuizzes] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    axios.get(`${process.env.REACT_APP_API_QUIZ_URL}/all`)
      .then(response => {
        setQuizzes(response.data);
        setLoading(false);
      })
      .catch(error => {
        console.error("Error fetching quizzes:", error);
        setLoading(false);
      });
  }, []);

  return (
    <div className="max-w-3xl w-full p-6 bg-white shadow-lg rounded-xl">
      <h1 className="text-2xl font-bold mb-4 text-center">Available Quizzes</h1>
      {loading ? (
        <p className="text-center text-gray-500">Loading...</p>
      ) : quizzes.length > 0 ? (
        <ul className="space-y-3">
          {quizzes.map((quiz) => (
            <li key={quiz.id} className="border p-4 rounded-lg shadow hover:bg-gray-100">
              <div className="flex items-center justify-between">
                <div>
                  <h2 className="text-lg font-semibold">{quiz.title}</h2>
                  <p className="text-sm text-gray-600">No of questions: {quiz.questions.length}</p>
                </div>
              
              <Link
                to={`/quiz/${quiz.id}`}
                className="mt-2 inline-block px-4 py-2 bg-blue-600 text-white rounded-lg"
              >
                Start Quiz
              </Link>
              </div>
            </li>
          ))}
        </ul>
      ) : (
        <p className="text-center text-gray-500">No quizzes available.</p>
      )}
    </div>
  );
};

export default HomePage;
