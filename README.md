# Web-based Part for ScopingSim

This project was done not only as a course project in Interactive System Design course but also a practical project cooperated with medical school. We have taken charge of developing the web-based part using MVC framework. 

Here is a presentation video: https://www.youtube.com/watch?v=rC3hY4Hsub4 

## What is ScopingSim?
ScopingSim is an endoscopy, colonoscopy, and bronchoscopy simulator project. The team is creating an inexpensive modular scoping simulator application that will integrate the web-based simulation authoring software Raspberry PI, a low-cost computing platform; Arduino, an open-source hardware; and a variety of off-the-shelf sensors. The final result will allow educational and medical institutions to take advantage of transportable, cost-effective, and high-fidelity training opportunities.

## What we have done?
We have developed a website allowing medical educators to do the following operations:

1. Uploading endoscopy video: System should synchronize the movement of sensor with the video playing, but now we use the mouse movement to fake the sensor movement. 
2. Adding note in any position of the video, which will be stored as time based.
3. Adding quiz (Three types: text quiz, multiple choices and checkbox) and corresponding answers.

## What technologies we have used?
* **Spark web framework**: A tiny mvc web framework providing us a faster development experience with high efficiency
* **Front-end**: HTML, CSS (Bootstrap), JavaScript
* **Database**: Java, MySQL 
