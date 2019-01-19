package hanze.nl.bussimulator;

public enum  Lines {

    LIJN1 (new Line.Stop[]{
            new Line.Stop(Halte.A,1),
            new Line.Stop(Halte.B,1),
            new Line.Stop(Halte.C,1),
            new Line.Stop(Halte.D,1),
            new Line.Stop(Halte.E,1),
            new Line.Stop(Halte.F,1),
            new Line.Stop(Halte.G,1)}),

    LIJN2 (new Line.Stop[]{
            new Line.Stop(Halte.H,1)
            ,new Line.Stop(Halte.E,1),
            new Line.Stop(Halte.I,1),
            new Line.Stop(Halte.K,1),
            new Line.Stop(Halte.L,1),
            new Line.Stop(Halte.M,1)}),

    LIJN3 (new Line.Stop[]{
            new Line.Stop(Halte.N,1),
            new Line.Stop(Halte.O,1),
            new Line.Stop(Halte.P,1),
            new Line.Stop(Halte.K,-1),
            new Line.Stop(Halte.Q,1),
            new Line.Stop(Halte.R,1)}),

    LIJN4 (new Line.Stop[]{
            new Line.Stop(Halte.S,1),
            new Line.Stop(Halte.T,1),
            new Line.Stop(Halte.U,1),
            new Line.Stop(Halte.V,1),
            new Line.Stop(Halte.I,1),
            new Line.Stop(Halte.K,1),
            new Line.Stop(Halte.L,1),
            new Line.Stop(Halte.M,1)}),

    LIJN5 (new Line.Stop[]{
            new Line.Stop(Halte.W,1),
            new Line.Stop(Halte.X,1),
            new Line.Stop(Halte.Y,1),
            new Line.Stop(Halte.G,1),
            new Line.Stop(Halte.Z,1),
            new Line.Stop(Halte.N,1)}),

    LIJN6 (new Line.Stop[]{
            new Line.Stop(Halte.A,1),
            new Line.Stop(Halte.B,1),
            new Line.Stop(Halte.H,1),
            new Line.Stop(Halte.T,-1),
            new Line.Stop(Halte.X,-1),new Line.Stop(Halte.W,-1)}),

    LIJN7 (new Line.Stop[]{
            new Line.Stop(Halte.A,1),
            new Line.Stop(Halte.R,-1),
            new Line.Stop(Halte.Q,-1),
            new Line.Stop(Halte.L,1),
            new Line.Stop(Halte.M,1),
            new Line.Stop(Halte.O,-1),
            new Line.Stop(Halte.N,-1),
            new Line.Stop(Halte.Z,-1),
            new Line.Stop(Halte.G,-1),
            new Line.Stop(Halte.Y,-1),
            new Line.Stop(Halte.X,-1),
            new Line.Stop(Halte.W,-1)}),

    LIJN8 (new Line.Stop[]{
            new Line.Stop(Halte.M,-1),
            new Line.Stop(Halte.P,1),
            new Line.Stop(Halte.J,1),
            new Line.Stop(Halte.F,-1),
            new Line.Stop(Halte.V,-1),
            new Line.Stop(Halte.E,-1),
            new Line.Stop(Halte.H,-1)});
}
