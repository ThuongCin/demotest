package com.Controller;


import com.Entity.Product;
import com.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
public class GiayController {
    @Autowired
    private ProductService ProductService;

    @GetMapping("/giaylist")
    public String sanphamlist(Model model, @RequestParam(name = "timkiem", required = false) String timkiem) {
        List<Product> giays = ProductService.getAll();
        if (timkiem != null && !timkiem.isEmpty()) {
            giays = ProductService.searchGiay("%" + timkiem + "%");
            if (giays.isEmpty()) {
                model.addAttribute("thongbaotimkiemno", "Không tìm thấy sản phẩm !");
            }
        } else {
            model.addAttribute("thongbaotimkiemchuanhap", "Bạn hãy nhập tên sản phẩm để tìm kiếm !!");
        }
        if (timkiem != null && !timkiem.isEmpty()) {
            model.addAttribute("giays", giays);
            
        }
        return "product";
    }
    @GetMapping("/addsanpham")
    public String adduser(Model model) {
        model.addAttribute("giay", new Product());
        return "add";
    }
    
    @PostMapping("/save")
    public  String save(@ModelAttribute("giay") Product giay, Model model, RedirectAttributes redirectAttributes) {
        if (
                giay.getTensp() == null || giay.getTensp().isEmpty() || 
                        giay.getGia() == null || giay.getGia().isEmpty() ||
                        giay.getMota() == null || giay.getMota().isEmpty() ||
                        giay.getHinhanh() == null || giay.getHinhanh().isEmpty()) {
            model.addAttribute("message","Xin vui lòng nhập đủ thông tin !");
            return "add";
        } else {
            if (giay.getId() == null) {
                redirectAttributes.addFlashAttribute("thongbaothanhcong","Thêm sản phẩm thành công !");
            }else  {
                redirectAttributes.addFlashAttribute("thongbaothanhcong", "Sửa sản phẩm thành công !");
            }
            ProductService.save(giay);
            return "redirect:/product";
        }
    }
    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id, RedirectAttributes redirectAttributes) {
      ProductService.delete(id);
      redirectAttributes.addFlashAttribute("messagedelete","Sản phẩm đã được xóa thành công !");
      return "redirect:/product";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam("id") Long id, Model model) {
        Product product = ProductService.findById(id);
        model.addAttribute("giay", product);
        return "update";
    }
    //hinhanh
    @GetMapping("/image/{hinhanhsanpham}")
    public ResponseEntity<byte[]> getImage(@PathVariable("hinhanhsanpham") String hinhanhsanpham) {
        try {
            File file = new File("src/main/resources/image/" + hinhanhsanpham);
            byte[] imageBytes = Files.readAllBytes(file.toPath());
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG);
            return  new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
