package ru.gb;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/static/**")
//                .addResourceLocations("classpath:/static/");
//    }

        @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String location = "publication-images";
        Path publicationImagesDir = Paths.get(location);

        String publicationImagesPath = publicationImagesDir.toFile().getAbsolutePath();

            System.out.println(publicationImagesPath);

            registry.addResourceHandler("/" +location + "/**")
                .addResourceLocations("file:"+publicationImagesPath+"/");

        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");

    }

}

