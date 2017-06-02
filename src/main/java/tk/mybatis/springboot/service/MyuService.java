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

package tk.mybatis.springboot.service;

import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import tk.mybatis.springboot.mapper.MyuTestMapper;
import tk.mybatis.springboot.mapper.MyuMapper;
import tk.mybatis.springboot.model.MyuTest;

import java.util.List;

/**
 * @author liuzh
 * @since 2015-12-19 11:09
 */
@Service
public class MyuService {

    @Autowired
    private MyuMapper myuMapper;

    public List<MyuTest> getAll(MyuTest myuTest) {
        if (myuTest.getPage() != null && myuTest.getRows() != null) {
            PageHelper.startPage(myuTest.getPage(), myuTest.getRows());
        }
        return myuMapper.selectAll();
    }

    public MyuTest getById(Integer id) {
        return myuMapper.selectByPrimaryKey(id);
    }

    public void deleteById(Integer id) {
        myuMapper.deleteByPrimaryKey(id);
    }

    public void save(MyuTest country) {
        if (country.getId() != null) {
            myuMapper.updateByPrimaryKey(country);
        } else {
            myuMapper.insert(country);
        }
    }
}
