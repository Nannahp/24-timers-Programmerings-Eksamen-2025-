/** @type {import('tailwindcss').Config} */
export default {
  content: ["./src/**/*.{vue,js,ts,jsx,tsx,html}"],
  theme: {
    extend: {},
  },
  plugins: [
    function ({ addUtilities }) {
      addUtilities(
        {
          ".writing-mode-vertical-lr": {
            "writing-mode": "vertical-lr",
          },
          ".writing-mode-vertical-rl": {
            "writing-mode": "vertical-rl",
          },
          ".text-orientation-mixed": {
            "text-orientation": "mixed",
          },
          ".text-orientation-upright": {
            "text-orientation": "upright",
          },
        },
        ["responsive", "hover"]
      );
    },
  ],
};
