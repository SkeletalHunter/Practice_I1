import java.io.*;
import java.util.*;

public class Pr4B {
	BufferedReader br;
	StringTokenizer in;
	PrintWriter out;
	static int N, MAX = Integer.MAX_VALUE / 2;

	public String nextToken() throws IOException {
		while (in == null || !in.hasMoreTokens()) {
			in = new StringTokenizer(br.readLine());
		}
		return in.nextToken();
	}

	public int nextInt() throws IOException {
		return Integer.parseInt(nextToken());
	}

	public double nextDouble() throws IOException {
		return Double.parseDouble(nextToken());
	}

	public long nextLong() throws IOException {
		return Long.parseLong(nextToken());
	}

	public static void main(String[] args) throws IOException {
		new Pr4B().run();
	}

	public void solve() throws IOException {
		N = nextInt();
		int M = nextInt();
		int[][] graph = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (i == j) {
					graph[i][j] = 0;
				} else {
					graph[i][j] = MAX;
				}
			}
		}
		for (int i = 0; i < M; i++) {
			int from = nextInt() - 1;
			int to = nextInt() - 1;
			int cost = nextInt();
			graph[from][to] = cost;
		}
		
		int result[][] = floyd_warshall(graph);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				out.print(result[i][j] + " ");
			}
			out.println();
		}
	}

	public static int[][] floyd_warshall(int graph[][]) {
		for (int center = 0; center < N; center++) {
			for (int start = 0; start < N; start++) {
				for (int end = 0; end < N; end++) {
					if (graph[start][center] < MAX && graph[center][end] < MAX
							&& graph[start][center] + graph[center][end] < graph[start][end])
						graph[start][end] = graph[start][center] + graph[center][end];
				}
			}
		}
		return graph;

	}

	public void run() {
		try {
			br = new BufferedReader(new FileReader("pathsg.in"));
			out = new PrintWriter("pathsg.out");

			solve();

			out.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
}