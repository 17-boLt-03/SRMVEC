% -------- 8-Puzzle BFS Solver --------

move(State, NewState) :- move_left(State, NewState).
move(State, NewState) :- move_right(State, NewState).
move(State, NewState) :- move_up(State, NewState).
move(State, NewState) :- move_down(State, NewState).
move_left([0,A,B|T],   [A,0,B|T]).
move_left([A,0,B|T],   [A,B,0|T]).
move_left([A,B,C|T],   [A,B,C|T1]) :- move_left(T, T1).
move_right([A,B,0|T],  [A,0,B|T]).
move_right([A,0,B|T],  [0,A,B|T]).
move_right([A,B,C|T],  [A,B,C|T1]) :- move_right(T, T1).
move_up([0,A,B,C|T],   [C,A,B,0|T]).
move_up([H|T],         [H|T1])      :- move_up(T, T1).
move_down([A,B,C,0|T], [0,B,C,A|T]).
move_down([H|T],       [H|T1])      :- move_down(T, T1).
goal([1,2,3,4,5,6,7,8,0]).
puzzle(State) :-
    bfs([[State]], [], SolutionPath),
    reverse(SolutionPath, OrderedPath),
    write_solution(OrderedPath, 0).
bfs([[State|Path]|_], _, [State|Path]) :-
    goal(State), !.
bfs([[State|Path]|Rest], Visited, Solution) :-
    findall(
        [NextState, State | Path],
        ( move(State, NextState),
          \+ member(NextState, [State|Path]),
          \+ member(NextState, Visited)
        ),
        NewPaths
    ),
    append(Rest, NewPaths, Queue),
    bfs(Queue, [State|Visited], Solution).
write_solution([], _).
write_solution([State|Rest], N) :-
    format("Step ~w:\n", [N]),
    print_state(State),
    nl,
    N1 is N + 1,
    write_solution(Rest, N1).
print_state([]).
print_state([A,B,C|T]) :-
    format("~w ~w ~w\n", [A,B,C]),
    print_state(T).
start :-
    InitialState = [1,2,3,4,5,6,0,7,8],   % Change this to any solvable state
    puzzle(InitialState).
    
?- start.
