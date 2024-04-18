import React, { useState } from 'react';
import { BsGearFill } from 'react-icons/bs';
import { FaHandsHelping, FaUserCircle } from 'react-icons/fa';
import { BiLogOutCircle } from "react-icons/bi";
import { FiActivity } from "react-icons/fi";
import { Link } from 'react-router-dom';
import { IoChevronForwardCircleOutline } from "react-icons/io5";
import { TbLayoutSidebarRightCollapseFilled } from "react-icons/tb";

const SideBar = () => {
  const [showSidebar, setShowSidebar] = useState(false);

  const toggleSidebar = () => {
    setShowSidebar(!showSidebar);
  };

  return (
    <div className="sidebar-icons-container">
      <SideBarButton onClick={toggleSidebar} />
      <div className={`md:flex md:flex-col md:items-center md:relative ${showSidebar ? '' : 'hidden'}`}>
        <SideBarIcon icon={<FaUserCircle size="30" />} text="Profile" to="/Profile" />
        <Divider/>
        <SideBarIcon icon={<FiActivity size="30" />} text="Activity" to="/Activity" />
        <Divider/>
        <SideBarIcon icon={<BsGearFill size="30" />} text="Settings" to="/Setting"  />
        <Divider/>
        <SideBarIcon icon={<FaHandsHelping size="30" />} text="Help" to="/Help" />
        <Divider/>
        <SideBarIcon icon={<BiLogOutCircle  size="30" />} text="Logout" />
      </div>
    </div>
  );
};


const SideBarButton = ({ onClick }) => (
  <button className="fixed top-1/4 dark:text-white left-1 transform -translate-y-1/2 md:hidden sidebar-button"  onClick={onClick}>
    
    <TbLayoutSidebarRightCollapseFilled size="25" />
  </button>
);

const SideBarIcon = ({ icon, text ,to }) => (
  <Link to={to} className="sidebar-icon group">
    {icon}
    <span className="sidebar-tooltip group-hover:scale-100">
      {text}
    </span>
  </Link>
);

const Divider = () => <hr className="sidebar-hr" />;

export default SideBar;
