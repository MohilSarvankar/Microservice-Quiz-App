import { useEffect, useState } from "react";
import axios from "axios";
import AdminModal from "../../components/AdminModal";

const ManageQuiz = () => {
    const [quizData, setQuizData] = useState({
        category: "",
        title: "",
        noOfQuestions: "",
    });

    const [categories, setCategories] = useState([]); 
    const [quizCreated, setQuizCreated] = useState(false);

    // Fetch categories from API
    useEffect(() => {
      const fetchCategories = async () => {
          try {
              const response = await axios.get(`${process.env.REACT_APP_API_URL}/question-service/question/categories`);
              setCategories(response.data);
          } catch (error) {
              console.error("Error fetching categories:", error);
          }
      };

      fetchCategories();
    }, []);

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
            const response = await axios.post(`${process.env.REACT_APP_API_URL}/quiz-service/quiz/create`, quizData);
            setQuizCreated(true);
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
                    <select
                        name="category"
                        value={quizData.category}
                        onChange={handleChange}
                        className="w-full p-2 border rounded-md"
                    >
                        <option value="">Select Category</option>
                        {categories.map((cat, index) => (
                            <option key={index} value={cat}>
                                {cat}
                            </option>
                        ))}
                    </select>
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

            {quizCreated && <AdminModal message={ "Quiz created successfully"} navigateTo="/admin"/>}
        </div>
    );
};

export default ManageQuiz;
