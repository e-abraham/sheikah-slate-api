import React from "react";

import logo from "../assets/logo.svg";

const Hero = () => (
  <div className="text-center hero my-5">
    <img className="mb-3 app-logo" src={logo} alt="Zelda" width="8200" />
    <h1 className="mb-4">React Sheikahs Slate Project</h1>
    <h1 className="mb-4">Created by Elizabeth, Ayman, Saleem,and Ricardo </h1>

    <p className="lead">
      This is an application that demonstrates an authentication flow for
      a SPA, using React and Mandy's tutorial.
    </p>
  </div>
);

export default Hero;
