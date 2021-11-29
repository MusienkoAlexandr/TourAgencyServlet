function filterPips(value, type) {
    if (type === 0) {
        return value < 2000 ? -1 : 0;
    }
    return value % 1000 ? 2 : 1;
}

var slider = document.getElementById('price_range');
noUiSlider.create(slider, {
    start: [0, 10000],
    connect: true,
    step: 100,
    orientation: 'horizontal', // 'horizontal' or 'vertical'
    range: {
        'min': 0,
        'max': 10000
    },

    pips: {
        mode: 'positions',
        values: [0, 25, 50, 75, 100],
        density: 4
    }
});