package com.rafaros.service.impl;

import com.rafaros.domain.Post;
import com.rafaros.repository.PostRepository;
import com.rafaros.service.PostService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Post}.
 */
@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final Logger log = LoggerFactory.getLogger(PostServiceImpl.class);

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post save(Post post) {
        log.debug("Request to save Post : {}", post);
        return postRepository.save(post);
    }

    @Override
    public Post update(Post post) {
        log.debug("Request to update Post : {}", post);
        return postRepository.save(post);
    }

    @Override
    public Optional<Post> partialUpdate(Post post) {
        log.debug("Request to partially update Post : {}", post);

        return postRepository
            .findById(post.getId())
            .map(existingPost -> {
                if (post.getTitle() != null) {
                    existingPost.setTitle(post.getTitle());
                }
                if (post.getBody() != null) {
                    existingPost.setBody(post.getBody());
                }
                if (post.getCategorie() != null) {
                    existingPost.setCategorie(post.getCategorie());
                }
                if (post.getResumee() != null) {
                    existingPost.setResumee(post.getResumee());
                }
                if (post.getAuteur() != null) {
                    existingPost.setAuteur(post.getAuteur());
                }
                if (post.getDatePersistence() != null) {
                    existingPost.setDatePersistence(post.getDatePersistence());
                }

                return existingPost;
            })
            .map(postRepository::save);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Post> findAll(Pageable pageable) {
        log.debug("Request to get all Posts");
        return postRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Post> findOne(Long id) {
        log.debug("Request to get Post : {}", id);
        return postRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Post : {}", id);
        postRepository.deleteById(id);
    }
}
