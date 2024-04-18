import React from 'react';

const Quote = () => {
  return (
    <div className="p-10 bg-gray-100 dark:bg-[#1C2222] text-gray-900  dark:text-white">
    <blockquote className="text-xl italic font-semibold text-center ">
      <svg
        className="w-10 h-10 mx-auto mb-3 text-gray-400 dark:text-[#0e161a]"
        aria-hidden="true"
        xmlns="http://www.w3.org/2000/svg"
        fill="currentColor"
        viewBox="0 0 18 14"
      >
        <path d="M6 0H2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4v1a3 3 0 0 1-3 3H2a1 1 0 0 0 0 2h1a5.006 5.006 0 0 0 5-5V2a2 2 0 0 0-2-2Zm10 0h-4a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4v1a3 3 0 0 1-3 3h-1a1 1 0 0 0 0 2h1a5.006 5.006 0 0 0 5-5V2a2 2 0 0 0-2-2Z"/>
      </svg>
      <p>"Turn every frame into a learning opportunity with CricShotAI - your ultimate cricket companion."</p>
      <hr className="w-48 h-1 mx-auto my-4 bg-gray-400 border-0 rounded md:my-6 dark:bg-[#0e161a]"></hr>
      <p>"From cover drives to reverse sweeps, CricShotAI deciphers every shot for a comprehensive analysis."</p>
      <svg
        className="w-10 h-10 mx-auto mt-3 text-gray-400 dark:text-[#0e161a]"
        aria-hidden="true"
        xmlns="http://www.w3.org/2000/svg"
        fill="currentColor"
        viewBox="0 0 18 14"
      >
        <path d="M6 0H2a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4v1a3 3 0 0 1-3 3H2a1 1 0 0 0 0 2h1a5.006 5.006 0 0 0 5-5V2a2 2 0 0 0-2-2Zm10 0h-4a2 2 0 0 0-2 2v4a2 2 0 0 0 2 2h4v1a3 3 0 0 1-3 3h-1a1 1 0 0 0 0 2h1a5.006 5.006 0 0 0 5-5V2a2 2 0 0 0-2-2Z"/>
      </svg>
    </blockquote>
    </div>
  );
};

export default  Quote;