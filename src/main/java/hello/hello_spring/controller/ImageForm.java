package hello.hello_spring.controller;

import jakarta.persistence.Column;

public class ImageForm {

    private String originFileName;
    private String path;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }
}
