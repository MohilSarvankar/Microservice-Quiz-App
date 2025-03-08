import React from 'react'

const QuizResultModal = ({ score, onClose }) => {
    return (
        <div className="fixed inset-0 bg-black bg-opacity-50 flex items-center justify-center">
            <div className="bg-white p-6 rounded-lg shadow-lg w-96 text-center">
                <h2 className="text-2xl font-bold text-gray-800">Quiz Completed!</h2>
                <p className="text-lg mt-2">Your Score: <span className="font-semibold">{score}</span></p>
                <button
                    onClick={onClose}
                    className="mt-4 px-6 py-2 bg-blue-500 text-white rounded-md hover:bg-blue-600"
                >
                    Done
                </button>
            </div>
        </div>
    );
}

export default QuizResultModal