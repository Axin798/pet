package org.andy.sensitivewdfilter;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : jingchenxi
 * @since : 2023/2/3
 **/
@RestController
public class controller {
    final WordFilter wordFilter;

    public controller(WordFilter wordFilter) {
        this.wordFilter = wordFilter;
    }
    @RequestMapping("/wordFilter")
    public String wordFilter(@RequestParam("str") String str) {
        return wordFilter.doFilter(str);
    }
}
