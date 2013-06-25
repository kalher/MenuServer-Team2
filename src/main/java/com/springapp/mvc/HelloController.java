package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import org.apache.commons.io.IOUtils;
//import sun.misc.IOUtils;

import javax.servlet.ServletContext;

@Controller
public class HelloController {
    @Autowired
    ServletContext servletContext;

	@RequestMapping(value = "/models", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "EatOut Menu ");
		model.addAttribute("dish1", "Chicken");
		model.addAttribute("dish2", "Katogo");
		model.addAttribute("dish3", "Beans on toast");
		model.addAttribute("dish4", "Beef");
		model.addAttribute("price1", "$50");
		model.addAttribute("price2", "$60");
		model.addAttribute("price3", "$70");
		model.addAttribute("price4", "$80");
		model.addAttribute("type", "Dish Type");
		model.addAttribute("price", "Price");


		return "hello";
	}

    @RequestMapping(value = "/hello", method = RequestMethod.GET, headers={"Accept=application/xml"})
    @ResponseBody
    public String printWelcomeXml() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<message>Kololo Courts</message>";

        return xml;
    }

    //for fried beef

    @RequestMapping(value = "/beef", method = RequestMethod.GET, headers={"Accept=application/xml"})
    @ResponseBody
    public String printFriedBeefXml() {
        String xml1 = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +

                "<message>Fried Beef</message>" +
                "<message>.           UGX 10,000</message>";


        return xml1;
    }

    @RequestMapping( value = "/picture", method = RequestMethod.GET , produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] renderPicture() throws IOException {
        String picturePath = "WEB-INF/pages/image/oso.jpeg";

        return getPicture(picturePath);
    }

    public byte[] getPicture(String path) throws IOException {
        InputStream resourceAsStream;
        resourceAsStream = servletContext.getResourceAsStream(path);

        return IOUtils.toByteArray(resourceAsStream);
    }
}