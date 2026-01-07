package com.example.xfash.Controller;

import com.example.xfash.Service.DeptService;
import com.example.xfash.pojo.Dept;
import com.example.xfash.pojo.Result;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/depts")
public class DeptController {
    @Autowired
    private DeptService deptService;

    //查询所有部门
    //@RequestMapping(value = "/depts",method = RequestMethod.GET)//method : 请求方式
    @GetMapping
    public Result list() {
//        System.out.println("查看全部部门信息：");
        log.info("查看全部部门信息：");
        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);
    }

    //    //删除指定部门 方法一
//    @DeleteMapping("/depts")
//    public Result delete(HttpServletRequest request){
//        String idStr=request.getParameter("id");
//        int id =Integer.parseInt(idStr);
//        System.out.println("根据id删除部门："+id);
//        return Result.success();
//    }
    //方法二：@RequestParam 必须传递参数，不然会出错（required 默认为True）
//    @DeleteMapping("/depts")
//    public Result delete(@RequestParam("id") Integer deptId) {
//        System.out.println("根据id删除部门：" + deptId);
//        return Result.success();
//    }
    //方式三 省略@RequestParam 前端请求参数和服务端方法参数必须一致可省略
    @DeleteMapping
    public Result delete(Integer id) {
//        System.out.println("根据id删除部门：" + id);
        log.info("根据id删除部门：{}", id);
        deptService.deleteById(id);
        return Result.success();
    }

    //增加部门
    @PostMapping
    public Result insert(@RequestBody Dept name) {
//        System.out.println("根据名字添加部门："+name);
        log.info("根据名字添加部门：{}", name);
        deptService.insert(name);
        return Result.success();
    }

    //根据id查询部门
    @GetMapping("/{id}")
    public Result search(@PathVariable Integer id) {
//        System.out.println("根据ID查询部门："+id);
        log.info("根据ID查询部门：{}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    //修改部门
    @PutMapping
    public Result update(@RequestBody Dept dept) {
//        System.out.println("修改部门："+dept);
        log.info("修改部门：{}", dept);
        deptService.update(dept);
        return Result.success();

    }

}
