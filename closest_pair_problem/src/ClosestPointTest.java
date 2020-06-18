import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import util.Point2;
import util.Point3;

import static org.testng.Assert.assertEquals;

public class ClosestPointTest {
	private static final String dataName = "d3";

	@DataProvider(name = "d1")
	public Object[][] DataProviders() {
	    Object[][] obj = new Object[][] {
			    {
					    new Long[]{
							    -621401557L,
							    -835637411L,
							    -298276777L,
							    639735446L,
							    141430313L,
							    226062408L
					    },
					    new Long[]{
							    141430313L,
							    226062408L
					    }
			    },
			    {
					    new Long[] {
							    66L,
							    17L,
							    32L,
							    25L,
							    46L,
							    24L,
					    },
					    new Long[] {
							    24L,
							    25L,
					    }
			    },
			    {
					    new Long[] {
							    -621401557L,
							    -835637411L,
					    },
					    new Long[] {
							    -835637411L,
							    -621401557L,
					    }
			    },
			    {
					    new Long[] {
							    -621401557L,
							    -835637411L,
							    -298276777L,
					    },
					    new Long[] {
							    -835637411L,
							    -621401557L,
					    }
			    },
			    {
					    new Long[] {
							    4L,
							    8L,
							    6L,
							    1L,
							    10L,
					    },
					    new Long[] {
							    4L,
							    6L,
					    }
			    },
			    {
					    new Long[] {
							    4L,
							    8L,
							    6L,
							    1L,
							    4L,
					    },
					    new Long[] {
							    4L,
							    4L,
					    }
			    },
	    };
	    return obj;
	}

	@DataProvider(name = "d2")
	public Object[][] DataProviders2() {
		Object[][] obj = new Object[][] {
				{
						new Point2[] {
								new Point2(342865007, -691265808),
								new Point2(168900467, -744748163),
								new Point2(-440434063, -184293515),
								new Point2(-691848451, -399926019),
								new Point2(609366198, 724389818),
								new Point2(537557125, 559390437),
						},
						new Point2[] {
								new Point2(537557125, 559390437),
								new Point2(609366198, 724389818),
						}
				},
				{
						new Point2[] {
								new Point2(1, 2),
								new Point2(1, 2),
								new Point2(-10, -11),
								new Point2(-20, -21),
								new Point2(30, 31),
								new Point2(40, 41),
						},
						new Point2[] {
								new Point2(1, 2),
								new Point2(1, 2),
						}
				},
				{
						new Point2[] {
								new Point2(1, 1),
								new Point2(2, 1),
								new Point2(3, 30),
						},
						new Point2[] {
								new Point2(1, 1),
								new Point2(2, 1),
						}
				}
		};
		return obj;
	}

	@DataProvider(name = "d3")
	public Object[][] DataProviders3() {
		Object[][] obj = new Object[][] {
				{
						new Point3[] {
								new Point3(5, 5, 8),
								new Point3(5, 5, 19),
								new Point3(5, 5, 2),
								new Point3(5, 5, 18),
								new Point3(5, 5, 6),
								new Point3(5, 5, 11),
						},
						new Point3[] {
								new Point3(5, 5, 18),
								new Point3(5, 5, 19),
						}
				},
		};
		return obj;
	}

	@Test(dataProvider = dataName)
	public void testMain(Object[] data, Object[] res) {
		switch (dataName) {
			case "d1":
				var res11 = (new solve.Dim1()).solve((Long[])data);
				assertEquals(res11, res);
				var res12 = naive.Dim1.solve((Long[])data);
				assertEquals(res12, res);
				break;
			case "d2":
				var res21 = (new solve.Dim2()).solve((Point2[])data);
				assertEquals(res21, res);
				var res22 = naive.Dim2.solve((Point2[])data);
				assertEquals(res22, res);
				break;
			case "d3":
				var res31 = (new solve.Dim3()).solve((Point3[])data);
				assertEquals(res31, res);
				var res32 = naive.Dim3.solve((Point3[])data);
				assertEquals(res32, res);
				break;
		}
	}

}