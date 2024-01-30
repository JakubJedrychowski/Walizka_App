import { Email, GitHub, LinkedIn } from "@material-ui/icons";
import React from "react";
import FooterCSS from "./Footer.module.css";

const Footer = () => {
  return (
    <div className={FooterCSS.Footer}>
      <div className={FooterCSS.footercontainer}>
        <h1>Tomasz Fela, Jakub Jędrychowski, Kacper Białas | 2024</h1>
      </div>
    </div>
  );
};

export default Footer;
