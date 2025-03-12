import { useState } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";

const ManageQuiz = () => {
    const [quizData, setQuizData] = useState({
        category: "",
        title: "",
        noOfQuestions: "",
    });

    const navigate = useNavigate();

    const handleChange = (e) => {
        setQuizData({ ...quizData, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();

        if (!quizData.category || !quizData.title || !quizData.noOfQuestions) {
            alert("Please fill in all fields.");
            return;
        }

        try {
            const response = await axios.post("http://localhost:8090/quiz/create", quizData);
            alert("Quiz created successfully!");
            navigate("/admin"); // Redirect back to Admin Dashboard
        } catch (error) {
            console.error("Error creating quiz:", error);
            alert("Failed to create quiz. Please try again.");
        }
    };

    return (
        <div className="max-w-3xl w-full mx-auto bg-white p-6 shadow-md rounded-md mt-10">
            <h2 className="text-2xl font-bold mb-4 text-center">Create New Quiz</h2>
            <form onSubmit={handleSubmit} className="space-y-4">

                {/* Quiz Title Input */}
                <div>
                    <input
                        type="text"
                        name="title"
                        value={quizData.title}
                        onChange={handleChange}
                        className="w-full p-2 border rounded-md"
                        placeholder="Quiz title"
                    />
                </div>

                {/* Category Input */}
                <div>
                    <input
                        type="text"
                        name="category"
                        value={quizData.category}
                        onChange={handleChange}
                        className="w-full p-2 border rounded-md"
                        placeholder="Quiz category"
                    />
                </div>

                {/* Number of Questions Input */}
                <div>
                    <input
                        type="number"
                        name="noOfQuestions"
                        value={quizData.noOfQuestions}
                        onChange={handleChange}
                        className="w-full p-2 border rounded-md"
                        placeholder="Number of questions"
                    />
                </div>

                {/* Submit Button */}
                <button
                    type="submit"
                    className="bg-blue-500 text-white py-2 rounded-md hover:bg-blue-600 px-5"
                >
                    Create Quiz
                </button>
            </form>
        </div>
    );
};

export default ManageQuiz;
