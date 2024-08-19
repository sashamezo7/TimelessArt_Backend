package DTO;


import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ArtworkDTO {
    public ArtworkDTO() {
    }

    private Long id;
    private String title;
    private String artworkType;
    private String description;
    private BigDecimal price;
    private boolean frame;
    private String technique;
    private Float length;
    private Float width;
    private Float height;
    private List<String> imageUrls;

}