import { useParams, useNavigate} from "react-router-dom";
import { useState, useEffect } from "react";
import axios from "axios";
import QuizResultModal from "../components/QuizResultModal";

const QuizPage = () => {
    const { id } = useParams();
    const [questions, setQuestions] = useState([]);
    const [answers, setAnswers] = useState({});
    const [loading, setLoading] = useState(true);
    const [score, setScore] = useState(null);
    const navigate = useNavigate();

    useEffect(() => {
        axios.get(`${process.env.REACT_APP_API_QUIZ_URL}/${id}/questions`)
            .then(response => {
                setQuestions(response.data);
                setLoading(false);
            })
            .catch(error => {
                console.error("Error fetching questions:", error);
                setLoading(false);
            });
    }, [id]);

    const handleOptionChange = (questionId, option) => {
        setAnswers(prevAnswers => ({
            ...prevAnswers,
            [questionId]: option
        }));
    };

    const handleSubmit = () => {
        const quizResponses = Object.entries(answers).map(([id, response]) => ({
            id: parseInt(id),
            response
        }));

        axios.post(`${process.env.REACT_APP_API_QUIZ_URL}/${id}/submit`, quizResponses)
            .then(response => {
                // alert(`You scored: ${response.data}`);
                setScore(response.data);
            })
            .catch(error => {
                console.error("Error submitting quiz:", error);
            });
    };

    if (loading) return <div className="text-center text-lg font-semibold">Loading...</div>;

    return (
        <div className="flex flex-col items-center justify-center min-h-screen bg-gray-100 p-6">
            <div className="w-full max-w-2xl bg-white shadow-lg rounded-lg p-6">
                <h2 className="text-2xl font-bold text-center mb-6">Quiz {id}</h2>
                {questions.map((question) => (
                    <div key={question.id} className="mb-6 border-b pb-4">
                        <h3 className="text-lg font-semibold mb-2">{question.questionTitle}</h3>
                        <div className="flex flex-col space-y-2">
                            {[question.option1, question.option2, question.option3, question.option4].map((option, index) => (
                                <label key={index} className="flex items-center space-x-2 bg-gray-50 hover:bg-gray-100 p-3 rounded-lg cursor-pointer">
                                    <input
                                        type="radio"
                                        name={`question-${question.id}`}
                                        value={option}
                                        checked={answers[question.id] === option}
                                        onChange={() => handleOptionChange(question.id, option)}
                                        className="w-5 h-5 text-blue-600"
                                    />
                                    <span className="text-gray-700">{option}</span>
                                </label>
                            ))}
                        </div>
                    </div>
                ))}
                <button
                    onClick={handleSubmit}
                    className="w-full bg-blue-600 text-white py-3 rounded-lg text-lg font-semibold hover:bg-blue-700 transition"
                >
                    Submit Quiz
                </button>
                {score !== null && <QuizResultModal score={score} onClose={() => navigate("/")} />}
            </div>
        </div>
    );
};

export default QuizPage;
