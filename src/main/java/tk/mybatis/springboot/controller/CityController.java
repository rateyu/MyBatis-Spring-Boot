/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2014-2016 abel533@gmail.com
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package tk.mybatis.springboot.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import tk.mybatis.springboot.model.MyuTest;
import tk.mybatis.springboot.service.MyuTestService;

import java.util.List;

/**
 * @author liuzh
 * @since 2015-12-19 11:10
 */
@RestController
@RequestMapping("/cities")
public class MyuTestController {

    @Autowired
    private MyuTestService MyuTestService;

    @RequestMapping
    public PageInfo<MyuTest> getAll(MyuTest MyuTest) {
        List<MyuTest> countryList = MyuTestService.getAll(MyuTest);
        return new PageInfo<MyuTest>(countryList);
    }

    @RequestMapping(value = "/add")
    public MyuTest add() {
        return new MyuTest();
    }

    @RequestMapping(value = "/view/{id}")
    public MyuTest view(@PathVariable Integer id) {
        ModelAndView result = new ModelAndView();
        MyuTest MyuTest = MyuTestService.getById(id);
        return MyuTest;
    }

    @RequestMapping(value = "/delete/{id}")
    public ModelMap delete(@PathVariable Integer id) {
        ModelMap result = new ModelMap();
        MyuTestService.deleteById(id);
        result.put("msg", "删除成功!");
        return result;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelMap save(MyuTest MyuTest) {
        ModelMap result = new ModelMap();
        String msg = MyuTest.getId() == null ? "新增成功!" : "更新成功!";
        MyuTestService.save(MyuTest);
        result.put("MyuTest", MyuTest);
        result.put("msg", msg);
        return result;
    }
}
