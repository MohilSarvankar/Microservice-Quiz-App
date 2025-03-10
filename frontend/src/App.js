import { BrowserRouter as Router, Route, Routes } from "react-router-dom";
import HomePage from "./pages/HomePage";
import QuizPage from "./pages/QuizPage";
import AdminDashboard from "./pages/admin/AdminDashboard";
import ManageQuiz from "./pages/admin/ManageQuiz";
import Navbar from "./components/Navbar";
import ManageQuestion from "./pages/admin/ManageQuestion";

function App() {

  return (
    <Router>
      <Navbar />
      <div className="min-h-screen bg-gray-100 flex justify-center items-center">
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/quiz/:quizId" element={<QuizPage />} />
          <Route path="/admin" element={<AdminDashboard />} />
          <Route path="/admin/quiz/:quizId" element={<ManageQuiz />} />
          <Route path="/admin/question/new" element={<ManageQuestion />} />  {/* Add Question */}
          <Route path="/admin/question/:id" element={<ManageQuestion />} />  {/* Edit Question */}
        </Routes>
      </div>
    </Router>
  );
}

export default App;
