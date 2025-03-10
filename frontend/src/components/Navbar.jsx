import { useState } from "react";
import { Link } from "react-router-dom";

const Navbar = () => {
  const [isOpen, setIsOpen] = useState(false);

  return (
    <nav className="bg-blue-600 text-white px-6 py-3 shadow-md">
      <div className="max-w-6xl mx-auto flex justify-between items-center">
        {/* Logo */}
        <Link to="/" className="text-2xl font-bold">
          Quiz App
        </Link>

        {/* Desktop Links */}
        <div className="hidden md:flex space-x-6">
          <Link to="/" className="hover:text-gray-300">Home</Link>
          <Link to="/admin" className="hover:text-gray-300">Admin Dashboard</Link>
          <button className="bg-red-500 px-4 py-1 rounded" onClick={() => alert("Logged out!")}>
            Logout
          </button>
        </div>

        {/* Mobile Menu Button */}
        <button className="md:hidden text-2xl" onClick={() => setIsOpen(!isOpen)}>
          ☰
        </button>
      </div>

      {/* Mobile Menu */}
      {isOpen && (
        <div className="md:hidden flex flex-col items-center mt-2 space-y-3 bg-blue-700 p-3 rounded">
          <Link to="/" className="hover:text-gray-300" onClick={() => setIsOpen(false)}>Home</Link>
          <Link to="/admin" className="hover:text-gray-300" onClick={() => setIsOpen(false)}>Admin Dashboard</Link>
          <button className="bg-red-500 px-4 py-1 rounded" onClick={() => alert("Logged out!")}>
            Logout
          </button>
        </div>
      )}
    </nav>
  );
};

export default Navbar;
