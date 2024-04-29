package com.wenyi.wenyi.controller;

import com.wenyi.wenyi.entity.*;
import com.wenyi.wenyi.service.PostsService;
import com.wenyi.wenyi.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.wenyi.wenyi.entity.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private PostsService postsService;

    /**
     * 发布帖子
     *
     * @param posts 帖子详情
     * @param token token
     * @return Result
     */
    @PostMapping("/savePostDraft")
    public Result savePostDraft(@RequestBody Posts posts, @RequestHeader(value = "Authorization", required = false) String token) {
        // 如果没有token，说明用户未登录，直接返回
        if (token == null || token.isEmpty()) {
            return Result.fail(402, "用户未认证");
        }
        // 获取用户信息
        User user = JwtUtil.getUserNameByToken(token);
        // 获取用户当前所有的草稿
        Posts posts1 = postsService.getDraftByUserId(user.getId());
        // 如果不等于空，进行更新
        if (posts1 != null) {
            posts.setPostId(posts1.getPostId());
            System.out.println(posts);
            return postsService.updatePostDraft(posts) ? Result.success(null, "保存草稿成功") : Result.fail(301, "保存失败");
        }
        // 否则插入
        posts.setSenderUserid(user.getId());
        Boolean addOk = postsService.addPost(posts);
        return addOk ? Result.success(null, "保存草稿成功") : Result.fail(301, "保存失败");
    }

    /**
     * 获取草稿
     *
     * @param token token
     * @return Result
     */
    @GetMapping("/getPostDraft")
    public Result getPostDraft(@RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || token.isEmpty()) {
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        Posts posts = postsService.getDraftByUserId(user.getId());
        return Result.success(posts);
    }

    /**
     * 删除草稿
     *
     * @param token
     * @return
     */
    @DeleteMapping("/deleteDraft")
    public Result deleteDraft(@RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || token.isEmpty()) {
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        Boolean isDelete = postsService.deletePostDraft(user.getId());
        return isDelete ? Result.success() : Result.fail(302, "删除失败");
    }

    /**
     * 获取所有已审核（postStatus == 2）帖子
     */
    @GetMapping("/allPosts")
    public Result getAllPosts() {
        List<Posts> postsList = postsService.getAllPosts();
        // 筛选出所有有封面的
        List<Posts> postsList1 = postsList.stream().filter(v -> !(v.getCoverImg() == null)).toList();
        if(!postsList1.isEmpty()) {
            return Result.success(sortPostsList(postsList));
        }else {
            return Result.success(sortPosts(postsList));
        }
    }

    /**
     * 获取用户所有的帖子
     */
    @GetMapping("/getUserPosts")
    public Result getUserAllPosts(@RequestHeader(value = "Authorization", required = false) String token, String userid) {
        if (token == null || token.isEmpty()) {
            return Result.fail(402, "用户未认证");
        }
        User user = JwtUtil.getUserNameByToken(token);
        List<Posts> postsList = postsService.getUserAllPostsByUserId(user.getId());
        postsList = postsList.stream().map((item) -> {
            item.setContent(null);
            return item;
        }).collect(Collectors.toList());
        if (!postsList.isEmpty()) {
            postsList = sortPosts(postsList);
        }
        return Result.success(postsList);
    }

    @GetMapping("/getPersonPosts")
    public Result getPersonAllPosts(Integer userId) {
        List<Posts> postsList = postsService.getUserAllPostsByUserId(userId);
        postsList = postsList.stream().map((item) -> {
            item.setContent(null);
            return item;
        }).collect(Collectors.toList());
        if (!postsList.isEmpty()) {
            postsList = sortPosts(postsList);
        }
        return Result.success(postsList);
    }


    /**
     * 根据帖子Id 获取该帖子
     *
     * @param postId 帖子id
     * @return
     */
    @GetMapping("/getPostById")
    public Result getPostByPostId(Integer postId) {
        Posts posts = postsService.getPostByPostId(postId);
        return Result.success(posts);
    }

    /**
     * 根据关键词找帖子
     * @param keyword
     * @return
     */
    @GetMapping("/getPostByKeyword")
    public Result getPostByKeyword(String keyword) {
        List<Posts> postsList = postsService.searchPosts(keyword);
        return Result.success(sortPosts(postsList));
    }

    /**
     * 获取精选帖子
     * @return
     */
    @GetMapping("/getGoodPost")
    public Result getGoodPost(){
        List<Posts> postsList = postsService.getGoodPosts();
        return Result.success(postsList);
    }

    private List<Posts> sortPostsList(List<Posts> postsList) {
        int j, cnt = 0;
        ArrayList<Posts> list1 = new ArrayList<>();
        ArrayList<Integer> list2 = new ArrayList<>();
        for (int i = 0; i < postsList.size(); i++) {
            if (postsList.get(i).getCoverImg() != null && !postsList.get(i).getCoverImg().trim().isEmpty()) {
                list2.add(i);
            }
        }
        for (int i = 0; i < list2.size(); i++) {
            if (i != 0 && i % 2 == 0) {
                for (j = list2.get(i - 1) + 1; j < list2.get(i); j++) {
                    list1.add(postsList.get(j));
                }
            }
            list1.add(postsList.get(list2.get(i)));
            ++cnt;
            if ((i + 1) % 2 == 0 && cnt == 2) {
                for (j = list2.get(i - 1) + 1; j < list2.get(i); j++) {
                    list1.add(postsList.get(j));
                }
                cnt = 0;
            }
        }
        if (!list2.isEmpty() && list2.get(list2.size() - 1) != postsList.size() - 1) {
            for (j = list2.get(list2.size() - 1) + 1; j < postsList.size(); j++) {
                list1.add(postsList.get(j));
            }
        }
        return list1;
    }

    private List<Posts> sortPosts(List<Posts> postsList){
        //
        List<Posts> postsList1 = new ArrayList<>(postsList.stream().filter(v -> {
            if (v.getCoverImg() == null) {
                return false;
            }
            return v.getCoverImg().isEmpty();
        }).toList());
        List<Posts> postsList2 = new ArrayList<>(postsList.stream().filter(v -> {
            if (v.getCoverImg() == null) {
                return false;
            }
            return !v.getCoverImg().isEmpty();
        }).toList());
        postsList1.addAll(postsList2);
        return postsList1;
    }


}
