import React from 'react';
import { useNavigate } from 'react-router-dom';

function Header() {
  const navigate = useNavigate();

  return (
    <div>
      <ul>
        <li onClick={() => navigate('/')}>Home</li>
        <li onClick={() => navigate('/about')}>About</li>
        <li onClick={() => navigate('/contact')}>Contact</li>
      </ul>
    </div>
  );
}

export default Header;
