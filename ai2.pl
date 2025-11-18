% -------- 8 Queens Problem (Backtracking Solution) --------

solution([]).
solution([c(X,Y)|Others]) :-
    solution(Others),
    member(Y, [1,2,3,4,5,6,7,8]),
    nonattack(c(X,Y), Others).

nonattack(_, []).
nonattack(c(X,Y), [c(X1,Y1)|Others]) :-
    Y =\= Y1,                             % Not same row
    abs(Y1 - Y) =\= abs(X1 - X),          % Not same diagonal
    nonattack(c(X,Y), Others).

member(X, [X|_]).
member(X, [_|T]) :-
    member(X, T).

% -------- Goal --------
?- solution([c(1,A), c(2,B), c(3,C), c(4,D), c(5,E), c(6,F), c(7,G), c(8,H)]).
