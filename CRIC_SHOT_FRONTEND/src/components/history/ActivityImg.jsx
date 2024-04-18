import React, { useState, useEffect } from 'react';
import { FaDownload } from "react-icons/fa6";
import { FaEye } from "react-icons/fa";
import { getCurrentUserDetail } from '../../auth';
import { getLast4MatchData } from '../../services/image_service';
const ActivityImg = () => {
  const [activities, setActivities] = useState([]);


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
    getLast4MatchData(user.id)
      .then((data) => {
       // const last4MatchData=data;
        setLast4MatchData(data);
      })
      .catch((error) => {
        console.log(error);
      });
  }
}, [user]);

  //spring end

  
  useEffect(() => {
    // Getting last 4 match Data
    if (last4MatchData.length > 0) {
    const dummyData = [
      {
        id: 1,
         date: last4MatchData[0]?.imageAddedDate,
         confidence: last4MatchData[0]?.confidence,
         shotType: last4MatchData[0]?.predicted_shot,
      
        predictedImages: [
          {url:  `data:image/png;base64,${last4MatchData[0]?.result_image_1}`},
          {url:  `data:image/png;base64,${last4MatchData[0]?.result_image_2}`},
          {url:  `data:image/png;base64,${last4MatchData[0]?.result_image_3}`},
        ]
      },
      {
        id: 2,
        date: last4MatchData[1]?.imageAddedDate,
        confidence: last4MatchData[1]?.confidence,
        shotType: last4MatchData[1]?.predicted_shot,
       
        predictedImages: [
          {url:  `data:image/png;base64,${last4MatchData[1]?.result_image_1}`},
          {url:  `data:image/png;base64,${last4MatchData[1]?.result_image_2}`},
          {url:  `data:image/png;base64,${last4MatchData[1]?.result_image_3}`},
        ]
      },
      {
        id: 3,
        date:last4MatchData[2]?.imageAddedDate,
        confidence: last4MatchData[2]?.confidence,
        shotType: last4MatchData[2]?.predicted_shot,
       
        predictedImages: [
          {url:  `data:image/png;base64,${last4MatchData[2]?.result_image_1}`},
          {url:  `data:image/png;base64,${last4MatchData[2]?.result_image_2}`},
          {url:  `data:image/png;base64,${last4MatchData[2]?.result_image_3}`},
        ]
      },
      {
        id: 4,
        date: last4MatchData[3]?.imageAddedDate,
        confidence: last4MatchData[3]?.confidence,
        shotType: last4MatchData[3]?.predicted_shot,
      
        predictedImages: [
          {url:  `data:image/png;base64,${last4MatchData[3]?.result_image_1}`},
          {url:  `data:image/png;base64,${last4MatchData[3]?.result_image_2}`},
          {url:  `data:image/png;base64,${last4MatchData[3]?.result_image_3}`},
        ]
      }
    ];

    setActivities(dummyData);
  }
  }, [last4MatchData]);


  const handleViewImage = (imageUrl) => {
    window.open(imageUrl, '_blank');
  };

  const handleDownloadImage = (imageUrl) => {
    fetch(imageUrl)
      .then(response => response.blob())
      .then(blob => {
        // Create a temporary URL for the blob object
        const blobUrl = window.URL.createObjectURL(blob);
        // Create a temporary anchor element
        const link = document.createElement('a');
        link.href = blobUrl;
        link.setAttribute('download', imageUrl.split('/').pop()); // Set the filename to the last part of the URL
        // Trigger a click event to start the download
        link.click();
        // Cleanup by revoking the object URL to prevent memory leaks
        window.URL.revokeObjectURL(blobUrl);
      })
      .catch(error => {
        console.error('Error downloading image:', error);
      });
};


  return (
    <div className="container mx-auto px-4 py-8">
      <h1 className="text-2xl font-semibold dark:text-white mb-4 mt-20">Uploaded Image History</h1>
      {activities.map((activity, index) => (
        <div key={activity.id} className="mb-4 border rounded-lg p-2 md:flex md:items-center md:justify-between  bg-white">
          <div className="mb-4 md:mb-0 md:mr-4 md:w-1/6 text-center">
            <p className="text-md font-semibold">Sr No. {index + 1}</p> 
          </div>
          <div className="md:w-2/6">
            <p className="text-md font-semibold">Date: {activity.date}</p>
            <p className="text-lg text-gray-500">Confidence: {activity.confidence}</p>
            <p className="text-lg text-gray-500">Predicted Shot: {activity.shotType}</p>
          </div>
          <div className="flex flex-wrap justify-between">
            {activity.predictedImages.map((image, idx) => (
              <div key={idx} className="flex flex-col items-center mb-2 md:mb-0">
                <img src={image.url} alt={`Prediction ${index + 1}`} className="w-24 h-auto object-contain rounded-sm mb-2 mr-2" />
                <div className="flex">
                  <button onClick={() => handleViewImage(image.url)} className="bg-blue-500 text-white px-2 py-1 rounded-lg mr-2 "><FaEye /></button>
                  <button onClick={() => handleDownloadImage(image.url)} className="bg-green-500 text-white px-2 py-1 rounded-lg"><FaDownload /></button>
                </div>
              </div>
            ))}
          </div>
        </div>
      ))}
    </div>
  );
};

export default ActivityImg;