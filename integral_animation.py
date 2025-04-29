from manim import *

class SecantIntegral(Scene):
    def construct(self):
        # Step 1: Display the integral at the center
        integral = MathTex(r"\int \sec(x)\,dx").move_to(ORIGIN)
        self.play(Write(integral))
        self.wait(1)
        self.play(
            integral.animate.scale(0.5).shift(LEFT * 4 + UP * 3)
        )
        self.wait(1)

        # Step 2: Show the trick
        trick = MathTex(
            r"= \int \frac{\sec(x)(\sec(x)+\tan(x))}{\sec(x)+\tan(x)}\,dx"
        ).move_to(ORIGIN + DOWN * 0.5)
        self.play(TransformFromCopy(integral, trick))
        self.wait(1)
        self.play(
            trick.animate.scale(0.5).shift(LEFT * 4 + UP * 2)
        )
        self.wait(1)

        # Step 3: Simplify numerator
        simplify = MathTex(
            r"= \int \frac{d}{dx}[\sec(x)+\tan(x)]}{\sec(x)+\tan(x)}\,dx"
        ).move_to(ORIGIN + DOWN * 0.5)
        self.play(TransformFromCopy(trick, simplify))
        self.wait(1)
        self.play(
            simplify.animate.scale(0.5).shift(LEFT * 4 + UP * 1)
        )
        self.wait(1)

        # Step 4: Show final result
        log_step = MathTex(
            r"= \ln|\sec(x) + \tan(x)| + C"
        ).move_to(ORIGIN + DOWN * 1)
        self.play(TransformFromCopy(simplify, log_step))
        self.wait(1)
        self.play(
            log_step.animate.scale(0.5).shift(LEFT * 4)
        )
        self.wait(1)

        self.play(
            integral.animate.scale(2).move_to(ORIGIN + UP * 2),
            trick.animate.scale(2).move_to(ORIGIN),
            simplify.animate.scale(2).move_to(ORIGIN + DOWN * 1), 
            log_step.animate.scale(2).move_to(ORIGIN + DOWN * 2)
        )
        self.wait(1)
        # Step 5: Fade out steps and center final answer
        self.play(FadeOut(integral, trick, simplify))
        self.play(log_step.animate.move_to(ORIGIN).scale(1.2))
        self.wait(2)
