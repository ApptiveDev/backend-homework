package apptive.basic.board.service;

import apptive.basic.board.dto.BoardRequestDto;
import apptive.basic.board.dto.BoardResponseDto;
import apptive.basic.board.entity.Board;
import apptive.basic.board.repository.BoardRepository;
import apptive.basic.user.entity.User;
import apptive.basic.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BoardService
{
    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    public BoardResponseDto createPost(BoardRequestDto request)
    {
        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if(optionalUser.isEmpty())
        {
            throw new IllegalArgumentException("회원이 존재하지 않습니다.");
        }

        User user = optionalUser.get();

        if(!user.getPassword().equals(request.getPassword()))
        {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if(request.getTitle().length()<10 || request.getTitle().length()>20)
        {
            throw new IllegalArgumentException("제목은 10자 이상 20자 이하이어야 합니다.");
        }

        if(request.getContent().length()<3 || request.getName().length()<3)
        {
            throw new IllegalArgumentException("내용과 이름은 3자 이상이어야 합니다.");
        }

        Board board = new Board();
        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setName(request.getName());

        Board saved = boardRepository.save(board);
        return new BoardResponseDto(saved.getId(), saved.getTitle(), saved.getContent(), saved.getName());
    }

    public BoardResponseDto getPost(Long id)
    {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("게시글이 존재하지 않습니다."));

        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContent(), board.getName());
    }

    public BoardResponseDto updatePost(Long id, BoardRequestDto request)
    {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if(optionalUser.isEmpty())
        {
            throw new IllegalArgumentException("회원이 존재하지 않습니다.");
        }

        User user = optionalUser.get();

        if(!user.getPassword().equals(request.getPassword()))
        {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        if(request.getTitle().length()<10 || request.getTitle().length()>20)
        {
            throw new IllegalArgumentException("제목은 10자 이상 20자 이하이어야 합니다.");
        }

        if(request.getContent().length()<3 || request.getName().length()<3)
        {
            throw new IllegalArgumentException("내용과 이름은 3자 이상이어야 합니다.");
        }

        board.setTitle(request.getTitle());
        board.setContent(request.getContent());
        board.setName(request.getName());

        return new BoardResponseDto(board.getId(), board.getTitle(), board.getContent(), board.getName());
    }

    public void deletePost(Long id, BoardRequestDto request)
    {
        Board board = boardRepository.findById(id)
                .orElseThrow(()->new IllegalArgumentException("게시글이 존재하지 않습니다."));

        Optional<User> optionalUser = userRepository.findByEmail(request.getEmail());
        if(optionalUser.isEmpty())
        {
            throw new IllegalArgumentException("회원이 존재하지 않습니다.");
        }

        User user = optionalUser.get();

        if(!user.getPassword().equals(request.getPassword()))
        {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        boardRepository.delete(board);
    }
}
