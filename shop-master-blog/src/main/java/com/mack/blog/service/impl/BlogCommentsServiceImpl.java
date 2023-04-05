package com.mack.blog.service.impl;

import com.mack.blog.mapper.BlogCommentsMapper;
import com.mack.blog.service.IBlogCommentsService;
import com.mack.common.entity.BlogComments;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Mack
 * @since 2022-11-22
 */
@Service
public class BlogCommentsServiceImpl extends ServiceImpl<BlogCommentsMapper, BlogComments> implements IBlogCommentsService {

}
