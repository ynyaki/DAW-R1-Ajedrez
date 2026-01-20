const main = document.querySelector("main");
const header = document.querySelector("header");
const video = document.querySelector("video");

const speedScroll = 0.65;

header.addEventListener('click', ()=>{
    console.log("asd");
    video.classList.toggle("hide");
});

main.addEventListener('wheel', (e) => {
    if (e.deltaY !== 0) {
        if (e.target.closest('.orb-card')) return;

        e.preventDefault();
        main.scrollLeft += e.deltaY * speedScroll;
    }
}, { passive: false });
