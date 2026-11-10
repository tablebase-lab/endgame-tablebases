# Endgame Tablebases

An independent Java project exploring **endgame tablebases**, **retrograde analysis**, and **efficient storage of chess positions**, with the longer-term goal of comparing endgames across games in the chess family.

An **endgame tablebase** records the outcome of a position under perfect play. Rather than estimating which side is better, it works with positions whose outcomes can be determined by analyzing the possible continuations. Because the number of positions grows rapidly as pieces are added, generating and storing these results becomes an interesting computational challenge.

## Current Work

I'm building the chess implementation and tablebase generator **from scratch**, without relying on an existing chess engine or tablebase library.

The project currently includes:

- **Chess logic:** Board and position representation, legal move generation, check detection, pawn promotion, and en passant.
- **Graphical interface:** A Java Swing chessboard viewer.
- **Retrograde analysis:** A generator focused on three-piece endgames: **KQK** (king and queen versus king), **KRK** (king and rook versus king), and **KPK** (king and pawn versus king).
- **Symmetry reduction:** Board rotations and reflections to avoid storing equivalent positions multiple times, with separate handling for pawn endgames.
- **Position indexing:** Precomputed position-index offsets to speed up tablebase lookups.
- **File storage:** Binary tablebase files accessed using Java's memory-mapped file functionality.

One of the largest performance improvements came from reworking how positions are indexed and eliminating redundant calculations. These changes brought my estimated three-piece generation time from roughly **348 hours to under five minutes**—an improvement of **over 4,000×**.

**Status:** This is an ongoing personal research and programming project, not a finished general-purpose chess engine or complete set of tablebases.

## Where I Want to Take It

My next goal is to complete **three- and four-piece endgame tablebases** for **Western chess** and **chaturanga**, an early Indian ancestor of modern chess. I want to compare their endgames, particularly the proportion of drawn positions, and investigate how differences in the rules affect those outcomes. My initial hypothesis is that chaturanga will produce a larger proportion of draws. Because the historical rules of chaturanga are not known with complete certainty, any comparison will specify the ruleset used.

In the longer term, I would also like to explore other games in the chess family, including **xiangqi**, and write up the findings from these comparisons.

A further technical goal is to generate **five-piece chess tablebases**. Under my current proposed storage scheme, I estimate they could take roughly **100 GB** at one byte per position, potentially reduced to about **25 GB** by encoding each position's outcome in two bits. These are projections, not completed five-piece results.

## Repository Structure

- `src/dev/michalrelich/engine/` — Chess rules, position handling, and graphical interface.
- `src/dev/michalrelich/tablebase/` — Retrograde analysis, position indexing, and tablebase storage.
- `data/three_piece/` — Three-piece tablebase data files.
