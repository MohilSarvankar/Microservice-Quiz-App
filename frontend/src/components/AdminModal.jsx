import { useNavigate } from "react-router-dom";

const AdminModal = ({ message, navigateTo }) => {
  const navigate = useNavigate();

  return (
    <div className="fixed inset-0 flex items-center justify-center bg-black bg-opacity-50">
      <div className="bg-white p-6 rounded-lg shadow-lg w-96 text-center">
        {/* Modal Message */}
        <p className="text-lg font-semibold mb-4">{message}</p>

        {/* Done Button */}
        <button 
          onClick={() => navigate(navigateTo)} 
          className="bg-blue-500 text-white px-4 py-2 rounded-md"
        >
          Done
        </button>
      </div>
    </div>
  );
};

export default AdminModal;
