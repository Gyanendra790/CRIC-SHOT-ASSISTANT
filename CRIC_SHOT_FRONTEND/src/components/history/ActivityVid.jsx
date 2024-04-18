import React, { useState, useEffect } from 'react';
import { FaDownload } from "react-icons/fa6";
import { FaEye } from "react-icons/fa";
import { getCurrentUserDetail } from '../../auth';
import { getLast4MatchVideoData } from '../../services/video_service';

const ActivityVid = () => {
  const [videoActivities, setVideoActivities] = useState([]);

//spring start
const [user, setUser] = useState(null);
const [last4MatchData, setLast4MatchData] = useState({});
  
useEffect(() => {
  const currentUser = getCurrentUserDetail();
  if (currentUser !== undefined) {
    setUser(currentUser);
  }
}, []);

useEffect(() => {
  if (user !== null) {
    //load last 4 match data
    getLast4MatchVideoData(user.id)
      .then((data) => {
        const last4MatchData=data;
        setLast4MatchData(data);
        console.log("Last 4 Match Data",last4MatchData);
      })
      .catch((error) => {
        console.log(error);
      });
  }
}, [user]);

  //spring end



  useEffect(() => {
    // last 3 video data
    if (last4MatchData.length > 0) {
    const dummyData = [
      {
         id: 1,
         date:last4MatchData[0]?.videoAddedDate ,
        numberOfShots:last4MatchData[0]?.shots_played,
        betterShot: last4MatchData[0]?.better_shot,
        weakShot: last4MatchData[0]?.weak_shot,
        predictedVideo: `data:video/mp4;base64,${last4MatchData[0]?.predicted_video}`
      },
      {
        id: 2,
        date:last4MatchData[1]?.videoAddedDate ,
        numberOfShots:last4MatchData[1]?.shots_played,
        betterShot: last4MatchData[1]?.better_shot,
        weakShot: last4MatchData[1]?.weak_shot,
        predictedVideo: `data:video/mp4;base64,${last4MatchData[1]?.predicted_video}`
      },
      {
        id: 3,
        date:last4MatchData[2]?.videoAddedDate ,
        numberOfShots:last4MatchData[2]?.shots_played,
        betterShot: last4MatchData[2]?.better_shot,
        weakShot: last4MatchData[2]?.weak_shot,
        predictedVideo: `data:video/mp4;base64,${last4MatchData[2]?.predicted_video}`
      },
    ];

    setVideoActivities(dummyData);
  }
  }, [last4MatchData]);

  const handleViewVideo = (videoUrl) => {
    window.open(videoUrl, '_blank');
  };

  const handleDownloadVideo = (videoUrl) => {
    const link = document.createElement('a');
    link.href = videoUrl;
    link.setAttribute('download', '');
    document.body.appendChild(link);
    link.click();
    document.body.removeChild(link);
  };

  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-2xl font-semibold mb-4 dark:text-white">Uploaded Video History</h1>
      <div className="grid grid-cols-1 lg:grid-cols-3 gap-8">
        {videoActivities.map((video, index) => (
          <div key={video.id} className="bg-white shadow-md rounded-lg overflow-hidden">
            <div className="p-4">
              <p className="text-lg font-semibold ">Sr no.  {index + 1}</p>
              <p className="text-gray-600 text-center">Date: {video.date}</p>
              <p className="text-gray-600 text-center">Number of Shots: {video.numberOfShots}</p>
              <p className="text-gray-600 text-center">Better Shot: {video.betterShot}</p>
              <p className="text-gray-600 text-center">Weak Shot: {video.weakShot}</p>
            </div>
            <div className="p-4 bg-gray-100">
              <p className="text-gray-700 mb-2">Predicted Video:</p>
              <div className="flex items-center justify-between">
                <video controls className="w-full rounded-lg">
                  <source src={video.predictedVideo} type="video/mp4" />
                  Your browser does not support the video tag.
                </video>
                <div>
                  <button onClick={() => handleViewVideo(video.predictedVideo)} className="bg-blue-500 text-white px-2 py-1 rounded-lg ml-2"><FaEye /></button>
                  <button onClick={() => handleDownloadVideo(video.predictedVideo)} className="bg-green-500 text-white px-2 py-1 rounded-lg ml-2"><FaDownload /></button>
                </div>
              </div>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};

export default ActivityVid;
